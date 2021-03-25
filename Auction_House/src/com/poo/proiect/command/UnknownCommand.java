package com.poo.proiect.command;

import java.util.Scanner;

public class UnknownCommand implements ICommand {
    private String commandString;
    private final Scanner scanner;

    public UnknownCommand(String commandString, Scanner scanner) {
        this.commandString = commandString;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (scanner.hasNextLine())
            commandString += scanner.nextLine();
        System.err.println("Unknown command: " + commandString);
    }
}
