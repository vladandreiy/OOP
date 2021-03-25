package com.poo.proiect;

import com.poo.proiect.auctionhouse.AuctionHouse;
import com.poo.proiect.command.ICommand;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuctionApp {
    private static boolean running = true;

    public static void closeApp() {
        AuctionApp.running = false;
    }

    public static void main(String[] args) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Scanner stdinScanner = new Scanner(System.in);
        while (running) {
            String commandString = stdinScanner.next();
            ICommand command = ICommand.getCommand(auctionHouse, executor, stdinScanner, commandString);
            command.execute();
        }
        executor.shutdown();
    }
}