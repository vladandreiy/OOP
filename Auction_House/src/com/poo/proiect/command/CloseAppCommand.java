package com.poo.proiect.command;

import com.poo.proiect.AuctionApp;

public class CloseAppCommand implements ICommand {
    @Override
    public void execute() {
        AuctionApp.closeApp();
    }
}
