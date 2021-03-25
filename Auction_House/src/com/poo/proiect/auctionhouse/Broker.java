package com.poo.proiect.auctionhouse;

import com.poo.proiect.client.Client;
import com.poo.proiect.client.LegalPerson;
import com.poo.proiect.client.Person;
import com.poo.proiect.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Broker extends Employee {
    private final List<Client> clients;
    private static double commission;
    static final int MAX_CLIENTS = 5;

    public Broker(AuctionHouse auctionHouse) {
        super(auctionHouse);
        this.clients = new ArrayList<>();
    }

    /**
     * @param product Product to be removed from AuctionHouse database
     */
    public void removeProduct(Product product) {
        auctionHouse.products.remove(product);
    }

    /**
     * @param client add Client to Broker
     */
    public void addClient(Client client) {
        clients.add(client);
    }

    public boolean isAvailable() {
        return this.clients.size() < MAX_CLIENTS;
    }

    public List<Client> getClients() {
        return clients;
    }

    /**
     * @param client Client to calculate commission for
     * @return commission for given Client
     */
    public static double getCommission(Client client) {
        if (client instanceof Person) {
            if (client.getAuctionsAttended() < 5)
                commission = 20.0 / 100;
            else
                commission = 15.0 / 100;
        }
        if (client instanceof LegalPerson) {
            if (client.getAuctionsAttended() < 25)
                commission = 25.0 / 100;
            else
                commission = 10.0 / 100;
        }
        return commission;
    }
}
