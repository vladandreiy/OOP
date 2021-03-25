package com.poo.proiect.auctionhouse;

import com.poo.proiect.client.Client;
import com.poo.proiect.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Auction {
    private final int id;
    private final Product product;
    private final List<Client> participants;
    static final int MINIMUM_PARTICIPANTS_NO = 2;
    static final int MAX_STEPS = 5;

    private static int auctionsNo = 0;

    public Auction(Product product) {
        this.product = product;
        this.id = auctionsNo++;
        this.participants = new ArrayList<>();
    }

    /**
     * @param participant Client to be added to this Auction
     */
    public void addParticipant(Client participant) {
        this.participants.add(participant);
    }

    /**
     * @return List of Clients participating in this Auction
     */
    public List<Client> getParticipants() {
        return participants;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public static int getAuctionsNo() {
        return auctionsNo;
    }
}
