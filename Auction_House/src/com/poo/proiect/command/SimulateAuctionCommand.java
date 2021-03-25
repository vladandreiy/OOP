package com.poo.proiect.command;

import com.poo.proiect.auctionhouse.AuctionHouse;
import com.poo.proiect.auctionhouse.AuctionSimulation;
import com.poo.proiect.auctionhouse.simulation.SleepHelper;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SimulateAuctionCommand implements ICommand {
    private final AuctionHouse auctionHouse;
    private int auctionsNo;
    private final ExecutorService executor;
    private static final int MAX_SIMULTANEOUS_AUCTIONS = 4;

    public SimulateAuctionCommand(AuctionHouse auctionHouse, int auctionsNo, ExecutorService executor) {
        this.auctionHouse = auctionHouse;
        this.auctionsNo = auctionsNo;
        this.executor = executor;
    }

    @Override
    public void execute() {
        int i = 0;
        Vector<Future<?>> futures = new Vector<>();
        while (auctionsNo != 0) {
            if (i < MAX_SIMULTANEOUS_AUCTIONS) {
                i++;
                Future<?> currentFuture = executor.submit(new AuctionSimulation(auctionHouse));
                futures.add(currentFuture);
                auctionsNo--;
                SleepHelper.sleep(3000);
            }
            for (Iterator<Future<?>> iterator = futures.iterator(); iterator.hasNext(); ) {
                Future<?> future = iterator.next();
                if (future.isDone()) {
                    i--;
                    iterator.remove();
                }
            }
        }
    }
}