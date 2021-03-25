package com.poo.proiect.auctionhouse;

import com.poo.proiect.auctionhouse.simulation.SleepHelper;
import com.poo.proiect.product.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URL;


public class Administrator extends Employee implements Runnable {
    private final String fileType;
    private final String fileName;

    /**
     * @param auctionHouse AuctionHouse that Administrator works for
     * @param fileType     CSV or URL
     * @param fileName     String containing url or csv file
     */
    public Administrator(AuctionHouse auctionHouse, String fileType, String fileName) {
        super(auctionHouse);
        this.fileType = fileType;
        this.fileName = fileName;
    }

    public void addProduct(Product product) {
        auctionHouse.products.add(product);
    }

    /**
     * The Administrator reads data from a csv file, either from a file or from a page that
     * represents a csv file and adds the products read to the database
     */
    @Override
    public void run() {
        if (this.fileType.equalsIgnoreCase("URL")) {
            try {
                readProductsURL(this.fileName);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            try {
                readProductsCsv(this.fileName);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void readProductsCsv(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        readProducts(fileReader);
    }

    public void readProductsURL(String url) throws IOException {
        InputStream input = new URL(url).openStream();
        Reader reader = new InputStreamReader(input);
        readProducts(reader);
    }

    /**
     * @param reader Reader used to read data from a csv file
     * @throws IOException if reader can't read from given file
     */
    private synchronized void readProducts(Reader reader) throws IOException {
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(reader);

        Product product;
        for (CSVRecord record : records) {
            String productType = record.get("product_type");
            String name = record.get("name");
            int year = Integer.parseInt(record.get("year"));
            double minimumPrice = Double.parseDouble(record.get("minimum_price"));
            if (minimumPrice <= 0) {
                try {
                    throw new NullOrNegativePriceException("Minimum selling price can't be lower or " +
                            "equal to 0. Current price is: " + minimumPrice);
                } catch (NullOrNegativePriceException exception) {
                    exception.printStackTrace();
                    continue;
                }
            }
            if (productType.equalsIgnoreCase("Furniture")) {
                String furnitureType = record.get("furniture_type");
                String furnitureMaterial = record.get("furniture_material");
                product = new Furniture(name, minimumPrice, year, furnitureType, furnitureMaterial);
            } else if (productType.equalsIgnoreCase("Painting")) {
                String paintingType = record.get("painting_type");
                String artistName = record.get("artist_name");
                product = new Painting(name, minimumPrice, year, artistName, paintingType);
            } else if (productType.equalsIgnoreCase("Jewelry")) {
                String jewelryMaterial = record.get("jewelry_material");
                boolean gemstone = Boolean.parseBoolean(record.get("gemstone"));
                product = new Jewelry(name, minimumPrice, year, jewelryMaterial, gemstone);
            } else
                try {
                    throw new ProductTypeNotFoundException("Product type is: " + productType);
                } catch (ProductTypeNotFoundException e) {
                    e.printStackTrace();
                    continue;
                }
            this.addProduct(product);
            SleepHelper.sleep(2000);
        }
    }
}
