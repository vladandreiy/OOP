package com.poo.proiect.client;

import com.poo.proiect.product.Product;

import java.util.List;
import java.util.Objects;

public abstract class Client {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected int auctionsAttended;
    protected int auctionsWon;
    protected List<Product> products;

    private static int clientsNo;

    protected Client() {
    }

    /**
     * @return client ID calculated based on amount of clients in AuctionHouse database
     */
    public String generateClientId() {
        if (this instanceof LegalPerson)
            return "CL" + clientsNo++;
        else
            return "CP" + clientsNo++;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", auctionsAttended=" + auctionsAttended +
                ", auctionsWon=" + auctionsWon +
                ", products=" + products +
                '}';
    }

    /**
     * @param product Product to be added to list of products owned by Client, won after an Auction
     */
    public synchronized void wonAuction(Product product) {
        this.auctionsWon++;
        this.products.add(product);
    }

    public synchronized void attendAuction() {
        this.auctionsAttended++;
    }

    public synchronized boolean attendedAuctions() {
        return this.auctionsAttended > 0;
    }

    public synchronized boolean wonAuctions() {
        return this.auctionsWon > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!Objects.equals(id, client.id)) return false;
        if (!Objects.equals(firstName, client.firstName))
            return false;
        if (!Objects.equals(lastName, client.lastName))
            return false;
        return Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAuctionsAttended() {
        return auctionsAttended;
    }

    public int getAuctionsWon() {
        return auctionsWon;
    }
}
