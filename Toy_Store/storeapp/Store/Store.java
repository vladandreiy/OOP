package storeapp.Store;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import storeapp.Exceptions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Store {
    private final String name;
    private final HashMap<String, Currency> currencies;
    private Currency currentCurrency;
    private final HashMap<String, Product> products;
    private final SortedMap<String, Manufacturer> manufacturers;
    private final List<Discount> discounts;

    private static Store storeInstance;


    public static Store getInstance(String name) {
        if (storeInstance == null) {
            storeInstance = new Store(name);
        }
        return storeInstance;
    }

    private Store(String name) {
        this.name = name;
        this.products = new LinkedHashMap<>();
        this.manufacturers = new TreeMap<>();
        this.currencies = new LinkedHashMap<>();
        this.currencies.put("EUR", new Currency("EUR", "€", 1.0));
        this.currentCurrency = this.currencies.get("EUR");
        this.discounts = new ArrayList<>();
    }

    // 1. Load/ Save data

    public void readCSV(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(fileReader);
        this.products.clear();
        this.manufacturers.clear();

        for (CSVRecord record : records) {
            if (record.get("price").equals("") || record.get("manufacturer").equals(""))
                continue;
            Manufacturer manufacturer;
            if (manufacturers.containsKey(record.get("manufacturer")))
                manufacturer = manufacturers.get(record.get("manufacturer"));
            else {
                manufacturer = new Manufacturer(record.get("manufacturer"));
                this.addManufacturer(manufacturer);
            }
            Product product = new Product.ProductBuilder(record.get("uniq_id"))
                    .setPrice(StringHelper.convertStringToPrice(record.get("price")))
                    .setName(record.get("product_name"))
                    .setQuantity(StringHelper.convertQuantity(record.get("number_available_in_stock")))
                    .setManufacturer(manufacturer).build();
            this.addProduct(product);
        }
        fileReader.close();
        this.countProductsByManufacturer();
    }

    public void saveCSV(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.EXCEL.withHeader("uniq_id",
                "product_name",
                "manufacturer",
                "price",
                "number_available_in_stock"));
        for (Map.Entry<String, Product> productEntry : products.entrySet()) {
            csvPrinter.printRecord(productEntry.getValue().getUniqueId(),
                    productEntry.getValue().getName(),
                    productEntry.getValue().getManufacturer().getName(),
                    currentCurrency.getSymbol() + productEntry.getValue().getPrice(),
                    productEntry.getValue().getQuantity());

        }
    }

    // 2.Manufacturers
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.put(manufacturer.getName(), manufacturer);
    }

    public void listManufacturers() {
        for (Map.Entry<String, Manufacturer> manufacturerEntry : manufacturers.entrySet())
            System.out.println(manufacturerEntry.getValue());
    }

    public void listProductsByManufacturer(String manufacturerName) throws ManufacturerNotFoundException {
        if (!manufacturers.containsKey(manufacturerName))
            throw new ManufacturerNotFoundException("storeApp.Store.Manufacturer " + manufacturerName + " not found in database");

        for (Map.Entry<String, Product> productEntry : products.entrySet()) {
            if (productEntry.getValue().getManufacturer().getName().equals(manufacturerName))
                printProductWithPrice(productEntry.getValue());
        }
    }

    public void countProductsByManufacturer() {
        for (Map.Entry<String, Product> productEntry : products.entrySet()) {
            int oldCount = productEntry.getValue().getManufacturer().getCountProducts();
            int newCount = oldCount + 1;
            productEntry.getValue().getManufacturer().setCountProducts(newCount);
        }
    }

    // 3. Products
    void printProductWithPrice(Product product) {
        System.out.println(product.getUniqueId() + ", " +
                product.getName() + ", " +
                product.getManufacturer().getName() + ", " +
                currentCurrency.getSymbol() + product.getPrice() + ", " +
                product.getQuantity());
    }

    public void addProduct(Product product) {
        products.put(product.getUniqueId(), product);
    }

    public void listProducts() {
        for (Map.Entry<String, Product> productEntry : products.entrySet()) {
            printProductWithPrice(productEntry.getValue());
        }
    }

    public void showProduct(String productId) throws ProductNotFoundException {
        if (products.containsKey(productId))
            printProductWithPrice(products.get(productId));
        else
            throw new ProductNotFoundException("storeApp.Store.Product " + productId + " doesn't exist in the database");
    }

    public void calculateTotal(Scanner scanner) throws ProductNotFoundException {
        String[] productsIds = scanner.nextLine().trim().split("\\s+");
        double total = 0;
        for (String product_id : productsIds) {
            if (products.containsKey(product_id))
                total += products.get(product_id).getPrice();
            else
                throw new ProductNotFoundException(product_id);
        }
        DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
        decimalFormat2.setRoundingMode(RoundingMode.UP);
        System.out.println(currentCurrency.getSymbol() + decimalFormat2.format(total));
    }

    // 4. Currency
    public void listCurrencies() {
        for (Map.Entry<String, Currency> currencyEntry : currencies.entrySet()) {
            System.out.println(currencyEntry.getValue());
        }
    }

    public void getStoreCurrency() {
        System.out.println(currentCurrency.getName());
    }

    public void addCurrency(Currency currency) throws NegativeOrNullParityException {
        if (currency.getParityToEur() <= 0)
            throw new NegativeOrNullParityException("Currency " + currency.getName() + " can't have null or negative parity to EUR");
        currencies.put(currency.getName(), currency);
    }

    public void setStoreCurrency(String currencyName) throws UnknownCurrencyException {
        if (!currencies.containsKey(currencyName))
            throw new UnknownCurrencyException("Currency " + currencyName + " doesn't exist in the database.");
        else {
            for (Map.Entry<String, Product> productEntry : products.entrySet()) {
                double oldPrice = productEntry.getValue().getPrice();
                double newPrice;
                // If new currency is EUR
                if (currencyName.equals("EUR"))
                    newPrice = oldPrice * currentCurrency.getParityToEur();
                    // If old currency is EUR
                else if (currentCurrency.getName().equals("EUR"))
                    newPrice = oldPrice / currencies.get(currencyName).getParityToEur();
                    // If neither old or new currency is EUR, first convert price to EUR
                else {
                    double eurPrice = oldPrice * currentCurrency.getParityToEur();
                    newPrice = eurPrice / currencies.get(currencyName).getParityToEur();
                }
                productEntry.getValue().setPrice(newPrice);
            }
            currentCurrency = currencies.get(currencyName);
        }
    }

    public void updateParity(String currencyName, double currencyParity) throws UnknownCurrencyException, NegativeOrNullParityException, IllegalEURParityException {
        if (currencyName.equals("EUR"))
            throw new IllegalEURParityException("EUR parity should always be 1");
        if (!currencies.containsKey(currencyName))
            throw new UnknownCurrencyException("Currency " + currencyName + " doesn't exist in the database.");
        else if (currencyParity <= 0)
            throw new NegativeOrNullParityException("Currency " + currencyName + " can't have null or negative parity to EUR");
        else {
            if (currentCurrency == currencies.get(currencyName)) {
                for (Map.Entry<String, Product> productEntry : products.entrySet()) {
                    double oldPrice = productEntry.getValue().getPrice();
                    double oldParity = currentCurrency.getParityToEur();
                    double newPrice = oldPrice * oldParity / currencyParity;
                    productEntry.getValue().setPrice(newPrice);
                }
            }
            currencies.get(currencyName).setParityToEur(currencyParity);
        }
    }

    // 5. Discounts

    public void listDiscounts() {
        for (Discount discount : discounts) {
            System.out.println(discount);
        }
    }

    public void addDiscount(Scanner scanner) throws UnknownDiscountTypeException {
        String discountTypeString = scanner.next();
        DiscountType discountType;
        if (discountTypeString.equals("PERCENTAGE"))
            discountType = DiscountType.PERCENTAGE_DISCOUNT;
        else if (discountTypeString.equals("FIXED"))
            discountType = DiscountType.FIXED_DISCOUNT;
        else {
            if (scanner.hasNextLine())
                scanner.nextLine();
            throw new UnknownDiscountTypeException("Unknown discount type: " + discountTypeString);
        }
        double discountValue = scanner.nextDouble();
        String discountName = scanner.nextLine().trim();
        discounts.add(new Discount(discountName, discountType, discountValue));
    }

    public void applyDiscount(String discountTypeString, double discountValue) throws UnknownDiscountTypeException, UnknownDiscountException {
        DiscountType discountType;
        if (discountTypeString.equals("PERCENTAGE"))
            discountType = DiscountType.PERCENTAGE_DISCOUNT;
        else if (discountTypeString.equals("FIXED"))
            discountType = DiscountType.FIXED_DISCOUNT;
        else {
            throw new UnknownDiscountTypeException("Unknown discount type: " + discountTypeString);
        }
        Discount currentDiscount = new Discount(discountType, discountValue);
        if (!discounts.contains(currentDiscount))
            throw new UnknownDiscountException("storeApp.Store.Discount " + currentDiscount);
        else {
            for (Map.Entry<String, Product> productEntry : products.entrySet())
                if (discountType == DiscountType.FIXED_DISCOUNT) {
                    if (discountValue > productEntry.getValue().getPrice()) {
                        System.err.println("Negative Price: Cannot apply discount to product " + productEntry.getValue());
                        continue;
                    }
                    double oldPrice = productEntry.getValue().getPrice();
                    double newPrice = oldPrice - discountValue;
                    productEntry.getValue().setPrice(newPrice);
                } else {
                    if (discountValue > 100) {
                        System.err.println("Negative Price: Cannot apply discount to product" + productEntry.getValue());
                        continue;
                    }
                    double oldPrice = productEntry.getValue().getPrice();
                    double newPrice = oldPrice - oldPrice * discountValue / 100;
                    productEntry.getValue().setPrice(newPrice);
                }
            discounts.get(discounts.indexOf(currentDiscount)).setAsAppliedNow();
        }
    }
}

class StringHelper {
    public static double convertStringToPrice(String priceString) {
        if (priceString.length() == 0)
            return 0;
        priceString = getFirstPrice(priceString);
        priceString = priceString.substring(1);
        int count = 0;
        if (priceString.contains(",")) {
            for (int i = 0; i < priceString.length(); i++) {
                if (priceString.charAt(i) == ',') {
                    count++;
                }
            }
        }
        priceString = priceString.replace(",", "");
        double price = Double.parseDouble(priceString);
        double multiplier = Math.pow(1000, count);
        return price * multiplier;
    }

    public static String getFirstPrice(String priceString) {
        if (priceString.contains("-")) {
            priceString = priceString.split("-")[0];
        }
        return priceString;
    }

    public static int convertQuantity(String quantityString) {
        quantityString = quantityString.replaceAll("[^0-9]", "");
        if (quantityString.equals(""))
            return 0;
        return Integer.parseInt(quantityString);
    }
}