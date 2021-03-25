package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.AuctionHouse;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public interface ICommand {
    void execute();

    static ICommand getCommand(AuctionHouse auctionHouse, ExecutorService executor, Scanner scanner, String commandString) {
        ICommand command;
        if (commandString.equalsIgnoreCase("quit"))
            command = new CloseAppCommand();
        else if (commandString.equalsIgnoreCase("read")) {
            String type = scanner.next();
            String fileType = scanner.next();
            command = new ReadCommand(type, auctionHouse, fileType, executor);
        } else if (commandString.toLowerCase().startsWith("simulateAuction".toLowerCase())) {
            String auctionsNoString = scanner.nextLine().trim();
            int auctionsNo;
            if (auctionsNoString.equals(""))
                auctionsNo = 1;
            else
                auctionsNo = Integer.parseInt(auctionsNoString);
            command = new SimulateAuctionCommand(auctionHouse, auctionsNo, executor);
        } else if (commandString.equalsIgnoreCase("print")) {
            String type = scanner.nextLine().trim();
            String condition;
            if (type.split(" ").length > 1) {
                condition = type.split(" ")[1];
                type = type.split(" ")[0];
                command = new PrintCommand(auctionHouse, type, condition);
            } else
                command = new PrintCommand(auctionHouse, type);
        } else {
            command = new UnknownCommand(commandString, scanner);
        }
        return command;
    }
}
