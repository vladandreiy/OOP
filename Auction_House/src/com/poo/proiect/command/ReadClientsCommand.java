package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.AuctionHouse;

public class ReadClientsCommand implements ICommand {
    private final AuctionHouse auctionHouse;
    private final String fileType;
    private static final String CLIENTS_URL = "https://my.api.mockaroo.com/clients.csv?key=665fa210";
    private static final String CLIENTS_FILE = "clients.csv";

    public ReadClientsCommand(AuctionHouse auctionHouse, String type) {
        this.auctionHouse = auctionHouse;
        this.fileType = type;
    }

    @Override
    public void execute() {
        if (fileType.equalsIgnoreCase("csv"))
            auctionHouse.readClientsCsv(CLIENTS_FILE);
        else if (fileType.equalsIgnoreCase("url"))
            auctionHouse.readClientsURL(CLIENTS_URL);
    }
}
