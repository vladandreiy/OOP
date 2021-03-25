package storeapp;

import storeapp.Exceptions.*;
import storeapp.Store.Currency;
import storeapp.Store.Store;

import java.io.IOException;
import java.util.Scanner;

public class StoreApp {
    private static final String CSV_NAME = "amazon_co-ecommerce_sample.csv";

    public static void main(String[] args) {
        String command;
        Scanner stdinScanner = new Scanner(System.in);

        while (true) {
            Store store = Store.getInstance("POO's Toy storeApp.Store.Store");
            command = stdinScanner.next();
            if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
                break;
            }
            // 1. Load/ Save data
            else if (command.equals("loadcsv")) {
                String filename = stdinScanner.nextLine().trim();
                if (filename.equals(""))
                    filename = CSV_NAME;
                try {
                    store.readCSV(filename);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else if (command.equals("savecsv")) {
                String filename = stdinScanner.next();
                try {
                    store.saveCSV(filename);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }

            // 2. Manufacturers
            else if (command.equals("listmanufacturers")) {
                store.listManufacturers();
            } else if (command.equals("listproductsbymanufacturer")) {
                String manufacturerName = stdinScanner.next();
                try {
                    store.listProductsByManufacturer(manufacturerName);
                } catch (ManufacturerNotFoundException exception) {
                    exception.printStackTrace();
                }
            }

            // 3. Products
            else if (command.equals("listproducts")) {
                store.listProducts();
            } else if (command.equals("showproduct")) {
                String productId = stdinScanner.next();
                try {
                    store.showProduct(productId);
                } catch (ProductNotFoundException exception) {
                    exception.printStackTrace();
                }
            } else if (command.equals("calculatetotal")) {
                try {
                    store.calculateTotal(stdinScanner);
                } catch (ProductNotFoundException exception) {
                    exception.printStackTrace();
                }
            }

            // 4. storeApp.Store.Currency
            else if (command.equals("listcurrencies"))
                store.listCurrencies();
            else if (command.equals("getstorecurrency"))
                store.getStoreCurrency();
            else if (command.equals("addcurrency")) {
                String currencyName = stdinScanner.next();
                String currencySymbol = stdinScanner.next();
                double currencyParity = stdinScanner.nextDouble();
                try {
                    store.addCurrency(new Currency(currencyName, currencySymbol, currencyParity));
                } catch (NegativeOrNullParityException exception) {
                    exception.printStackTrace();
                }
            } else if (command.equals("setstorecurrency")) {
                String currencyName = stdinScanner.next();
                try {
                    store.setStoreCurrency(currencyName);
                } catch (UnknownCurrencyException exception) {
                    exception.printStackTrace();
                }
            } else if (command.equals("updateparity")) {
                String currencyName = stdinScanner.next();
                double currencyParity = stdinScanner.nextDouble();
                try {
                    store.updateParity(currencyName, currencyParity);
                } catch (UnknownCurrencyException | NegativeOrNullParityException | IllegalEURParityException exception) {
                    exception.printStackTrace();
                }
            }

            // 5. Discounts
            else if (command.equals("listdiscounts")) {
                store.listDiscounts();
            } else if (command.equals("applydiscount")) {
                String discountTypeString = stdinScanner.next();
                double discountValue = stdinScanner.nextDouble();
                try {
                    store.applyDiscount(discountTypeString, discountValue);
                } catch (UnknownDiscountTypeException | UnknownDiscountException exception) {
                    exception.printStackTrace();
                }
            } else if (command.equals("adddiscount")) {
                try {
                    store.addDiscount(stdinScanner);
                } catch (UnknownDiscountTypeException exception) {
                    exception.printStackTrace();
                }
            }
            // Default Case
            else {
                if (stdinScanner.hasNextLine())
                    command += stdinScanner.nextLine();
                System.err.println("Unknown command: " + command);
            }
        }
    }
}
