package com.poo.proiect.auctionhouse;

import com.poo.proiect.auctionhouse.simulation.RandomHelper;
import com.poo.proiect.client.Client;
import com.poo.proiect.product.NullOrNegativePriceException;
import com.poo.proiect.product.Product;
import com.poo.proiect.auctionhouse.simulation.SleepHelper;

public class AuctionSimulation implements Runnable {
    private final AuctionHouse auctionHouse;

    public AuctionSimulation(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    /**
     * @return printing format so that auctions don't appear stacked on each other on stdout
     */
    private String calculateFormat() {
        String format = "\t";
        format = format.repeat(5 * auctionHouse.getAvailableIndex());
        return format;
    }

    /**
     * @param currentAuction get Id from currentAuction
     * @param format printing format
     * @return format with Auction Id
     */
    private String calculateFormatWithId(Auction currentAuction, String format) {
        return format + "#" + currentAuction.getId() + ": ";
    }

    /**
     * Simulate auction with random input and print the progress to stdout. Clients and products
     * are selected randomly from database and on every step are asked for their bid. Auction is
     * finished after MAX_STEPS. If the highest bid is bigger than the minimum price of the item,
     * the item is sold to the highest bidding client and he pays the price of the product and his
     * commission to the broker. The product is eliminated from the database and added to the client
     */
    @Override
    public synchronized void run() {
        Product currentProduct = getProduct();
        if (currentProduct == null)
            return;
        Auction currentAuction = new Auction(currentProduct);
        String format = calculateFormat();
        prepareAuction(currentProduct, currentAuction, format);
        String formatId = calculateFormatWithId(currentAuction, format);
        askClientsToJoin(currentAuction, formatId);
        System.out.println(formatId + "Auction starting");
        int highestBid = 0;
        Client highestBidClient = null;
        for (int i = 0; i < Auction.MAX_STEPS; i++) {
            for (Client participant : currentAuction.getParticipants()) {
                if (highestBidClient != null && participant.getId().equals(highestBidClient.getId()))
                    continue;
                SleepHelper.sleep(500);
                int currentBid = RandomHelper.getBid(highestBid);
                if (currentBid > highestBid) {
                    System.out.println(formatId + participant.getId() + " bid " + currentBid);
                    highestBid = currentBid;
                    highestBidClient = participant;
                }
            }
        }
        if (highestBidClient == null)
            return;
        finishedAuction(currentProduct, currentAuction, highestBid, highestBidClient, format);
    }

    private void prepareAuction(Product currentProduct, Auction currentAuction, String format) {
        auctionHouse.addActiveAuction(currentAuction);
        int clientNo = RandomHelper.getRandomInt(0, auctionHouse.clients.size());
        System.out.println("\n" + format + "***AUCTION " + currentAuction.getId() + "***");
        String formatId = calculateFormatWithId(currentAuction, format);
        System.out.println(formatId + auctionHouse.clients.get(clientNo) + " wants to start an auction on product ");
        System.out.println(format + currentProduct);
        SleepHelper.sleep(1000);
    }

    /**
     * Gets clients randomly from the database. Auction will start when there are at least
     * MINIMUM_PARTICIPANTS_NO participants
     * @param currentAuction current Auction
     * @param format printing format
     */
    private void askClientsToJoin(Auction currentAuction, String format) {
        while (currentAuction.getParticipants().size() < Auction.MINIMUM_PARTICIPANTS_NO) {
            for (Broker broker : auctionHouse.brokers) {
                for (Client brokerClient : broker.getClients()) {
                    if (clientAlreadyInAuction(currentAuction, brokerClient))
                        continue;
                    if (RandomHelper.getClientAnswer()) {
                        System.out.println(format + brokerClient.getId() + " joined the auction");
                        currentAuction.addParticipant(brokerClient);
                        brokerClient.attendAuction();
                        SleepHelper.sleep(500);
                    }
                }
            }
        }
    }

    /**
     * @param auction Auction to check in
     * @param client Client to be verified
     * @return true if client is participating in auction, false otherwise
     */
    private boolean clientAlreadyInAuction(Auction auction, Client client) {
        for (Client clientInAuction : auction.getParticipants()) {
            if (client.equals(clientInAuction))
                return true;
        }
        return false;
    }

    /**
     * @return a random Product from AuctionHouse database
     */
    private Product getProduct() {
        if (auctionHouse.products.isEmpty()) {
            return null;
        }
        int productNo = RandomHelper.getRandomInt(0, auctionHouse.products.size());
        Product currentProduct = auctionHouse.products.get(productNo);
        for (Auction auction : auctionHouse.activeAuctions) {
            if (auction == null)
                continue;
            if (productNo == auction.getProduct().getId())
                try {
                    throw new ProductAlreadyInAuctionException("Product " + currentProduct + " is already in an auction");
                } catch (ProductAlreadyInAuctionException e) {
                    e.printStackTrace();
                    return null;
                }
        }
        return currentProduct;
    }

    /**
     * @param soldProduct Product that the auction was started for
     * @param currentAuction Auction that took place
     * @param highestBid highest bid value
     * @param highestBidClient Client that had the highest bid
     * @param format printing format
     */
    private void finishedAuction(Product soldProduct, Auction currentAuction, int highestBid,
                                 Client highestBidClient, String format) {
        SleepHelper.sleep(2000);
        String formatId = calculateFormatWithId(currentAuction, format);
        if (highestBid < soldProduct.getMinimumPrice()) {
            System.out.println(formatId + "Product didn't sell");
        } else {
            System.out.println("\n" + formatId + soldProduct + " sold to");
            int priceWithCommission = (int) (highestBid + Broker.getCommission(highestBidClient) * highestBid);
            System.out.println(format + highestBidClient + " for " + priceWithCommission);
            try {
                soldProduct.setSellingPrice(highestBid);
            } catch (NullOrNegativePriceException exception) {
                exception.printStackTrace();
                return;
            }
            new Broker(AuctionHouse.getInstance()).removeProduct(soldProduct);
            highestBidClient.wonAuction(soldProduct);
            auctionHouse.removeProduct(soldProduct);
        }
        auctionHouse.removeAuction(currentAuction);
        System.out.println(format + "***AUCTION " + currentAuction.getId() + " ENDED ***\n");
        SleepHelper.sleep(2000);
    }
}

