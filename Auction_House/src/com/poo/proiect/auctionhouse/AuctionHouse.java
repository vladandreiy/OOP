package com.poo.proiect.auctionhouse;

import com.poo.proiect.client.*;
import com.poo.proiect.product.Product;
import com.poo.proiect.auctionhouse.utils.ICheckFunction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AuctionHouse {
    protected Vector<Product> products;
    protected List<Client> clients;
    protected Vector<Auction> activeAuctions;
    private static final int MAX_ACTIVE_AUCTIONS = 4;
    protected List<Broker> brokers;

    private static AuctionHouse instance;

    private AuctionHouse() {
        products = new Vector<>();
        clients = new ArrayList<>();
        activeAuctions = new Vector<>(4);
        for (int i = 0; i < MAX_ACTIVE_AUCTIONS; i++)
            activeAuctions.add(null);
        brokers = new ArrayList<>();
    }

    /**
     * (GOF) Singleton Pattern
     * @return instance of AuctionHouse
     */
    public static synchronized AuctionHouse getInstance() {
        if (instance == null)
            instance = new AuctionHouse();
        return instance;
    }

    /**
     * @param auction Auction to be added to activeAuctions list
     *                Cannot have more than MAX_ACTIVE_AUCTIONS active auctions
     */
    public synchronized void addActiveAuction(Auction auction) {
        for (int i = 0; i < MAX_ACTIVE_AUCTIONS; i++) {
            if (activeAuctions.get(i) == null) {
                activeAuctions.set(i, auction);
                break;
            }
        }
    }

    /**
     * @param auction Auction to be removed from activeAuctions list after it has ended
     */
    public synchronized void removeAuction(Auction auction) {
        if (activeAuctions.contains(auction))
            activeAuctions.set(activeAuctions.indexOf(auction), null);
    }

    /**
     * @param product Product to be removed from products list after it was sold
     */
    public synchronized void removeProduct(Product product) {
        products.remove(product);
    }

    public void addClient(Client client) {
        clients.add(client);
        if (brokers.isEmpty())
            brokers.add(new Broker(getInstance()));
        for (Broker broker : brokers) {
            if (broker.isAvailable()) {
                broker.addClient(client);
                return;
            }
        }
        Broker newBroker = new Broker(getInstance());
        newBroker.addClient(client);
        brokers.add(newBroker);
    }

    public void readClientsCsv(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            readClients(fileReader);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void readClientsURL(String url) {
        try {
            InputStream input = new URL(url).openStream();
            Reader reader = new InputStreamReader(input);
            readClients(reader);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Clients are read from a csv file, either from a file or from a page that
     * represents a csv file and adds the clients read to the AuctionHouse database
     * @param reader Reader used to read data from a csv file
     */
    private void readClients(Reader reader) throws IOException {
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(reader);

        for (CSVRecord record : records) {
            if (record.get("company_type").equals("")) {
                try {
                    this.addClient(new Person.PersonBuilder().setFirstName(record.get("first_name"))
                            .setLastName(record.get("last_name"))
                            .setAddress(record.get("address"))
                            .setBirthday(record.get("birthday"))
                            .build());
                } catch (UnderageClientException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    this.addClient(new LegalPerson.LegalPersonBuilder().setFirstName(record.get("first_name"))
                            .setLastName(record.get("last_name"))
                            .setAddress(record.get("address"))
                            .setCompanyType(record.get("company_type"))
                            .setCapitalStock(Double.parseDouble(record.get("company_capital")))
                            .build());
                } catch (NullOrNegativeCapitalStockException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return int value used for printing on stdout
     */
    public synchronized int getAvailableIndex() {
        for (int i = 0; i < 4; i++) {
            if (activeAuctions.get(i) == null)
                return i;
        }
        return 0;
    }

    /**
     * Print all clients in AuctionHouse database to stdout
     */
    public void printClients() {
        System.out.println("Clients: ");
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    /**
     * Print all clients in AuctionHouse database which respect a certain condition to stdout
     */
    public <T extends ICheckFunction> void printClients(T function) {
        System.out.println("Clients: ");
        for (Client client : clients) {
            if (function.check(client))
                System.out.println(client);
        }
    }

    /**
     * Print all products in AuctionHouse database to stdout
     */
    public synchronized void printProducts() {
        System.out.println("Products: ");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}

