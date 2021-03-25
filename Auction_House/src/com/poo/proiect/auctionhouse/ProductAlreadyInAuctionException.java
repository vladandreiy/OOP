package com.poo.proiect.auctionhouse;

public class ProductAlreadyInAuctionException extends Exception {
    public ProductAlreadyInAuctionException(String message) {
        super(message);
    }
}