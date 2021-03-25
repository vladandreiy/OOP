package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.AuctionHouse;

import java.util.concurrent.ExecutorService;

public class ReadCommand implements ICommand {
    private final String type;
    private final AuctionHouse auctionHouse;
    private final String fileType;
    private final ExecutorService executor;

    public ReadCommand(String type, AuctionHouse auctionHouse, String fileType, ExecutorService executor) {
        this.type = type;
        this.auctionHouse = auctionHouse;
        this.fileType = fileType;
        this.executor = executor;
    }

    @Override
    public void execute() {
        if (type.equalsIgnoreCase("clients"))
            new ReadClientsCommand(auctionHouse, fileType).execute();
        else if (type.equalsIgnoreCase("products"))
            new ReadProductsCommand(auctionHouse, fileType, executor).execute();
        else
            System.err.println("Unknown read type: " + type);
    }
}
