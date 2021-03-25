package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.AuctionHouse;
import com.poo.proiect.auctionhouse.utils.CheckAttendedAuction;
import com.poo.proiect.auctionhouse.utils.CheckWonAuctions;

public class PrintCommand implements ICommand {
    private final AuctionHouse auctionHouse;
    private final String type;
    private String condition;

    public PrintCommand(AuctionHouse auctionHouse, String type) {
        this.auctionHouse = auctionHouse;
        this.type = type;
    }

    public PrintCommand(AuctionHouse auctionHouse, String type, String condition) {
        this.auctionHouse = auctionHouse;
        this.type = type;
        this.condition = condition;
    }

    @Override
    public void execute() {
        if (type.equalsIgnoreCase("products"))
            auctionHouse.printProducts();
        else if (type.equalsIgnoreCase("clients")) {
            if (condition == null)
                auctionHouse.printClients();
            else if (condition.equalsIgnoreCase("won"))
                auctionHouse.printClients(new CheckWonAuctions());
            else if (condition.equalsIgnoreCase("attended"))
                auctionHouse.printClients(new CheckAttendedAuction());
            else
                System.err.println("Unknown print condition: " + condition);
        } else
            System.err.println("Unknown print type: " + type);
    }
}
