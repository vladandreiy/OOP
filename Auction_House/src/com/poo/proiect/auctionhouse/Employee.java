package com.poo.proiect.auctionhouse;

public abstract class Employee {
    public final AuctionHouse auctionHouse;

    protected Employee(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }
}
