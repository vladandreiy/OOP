package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.Administrator;
import com.poo.proiect.auctionhouse.AuctionHouse;

import java.util.concurrent.ExecutorService;

public class ReadProductsCommand implements ICommand {
    private final AuctionHouse auctionHouse;
    private final String fileType;
    private final ExecutorService executor;
    private static final String PRODUCTS_URL = "https://my.api.mockaroo.com/products.csv?key=665fa210";
    private static final String PRODUCTS_FILE = "products.csv";

    public ReadProductsCommand(AuctionHouse auctionHouse, String type, ExecutorService executor) {
        this.auctionHouse = auctionHouse;
        this.fileType = type;
        this.executor = executor;
    }

    @Override
    public void execute() {
        if (fileType.equalsIgnoreCase("csv"))
            executor.execute(new Administrator(auctionHouse, "csv", PRODUCTS_FILE));
        else if (fileType.equalsIgnoreCase("url"))
            executor.execute(new Administrator(auctionHouse, "url", PRODUCTS_URL));
    }
}
