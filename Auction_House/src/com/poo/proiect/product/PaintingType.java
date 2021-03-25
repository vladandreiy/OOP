package com.poo.proiect.product;

public enum PaintingType {
    OIL("OIL"),
    ACRYLIC("ACRYLIC"),
    WATERCOLOUR("WATERCOLOUR");
    private final String value;

    PaintingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
