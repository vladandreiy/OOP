package com.poo.proiect.client;

public enum CompanyType {
    SRL("SRL"),
    SA("SA");
    private final String value;

    CompanyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
