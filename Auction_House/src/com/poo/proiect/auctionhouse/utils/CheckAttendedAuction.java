package com.poo.proiect.auctionhouse.utils;

import com.poo.proiect.client.Client;

public class CheckAttendedAuction implements ICheckFunction {
    @Override
    public boolean check(Object o) {
        if (o instanceof Client)
            return ((Client) o).attendedAuctions();
        return false;
    }
}
