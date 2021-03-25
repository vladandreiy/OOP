package com.poo.proiect.client;

import java.util.ArrayList;

public class LegalPerson extends Client {
    private final CompanyType companyType;
    private final double capitalStock;

    private LegalPerson(LegalPersonBuilder builder) {
        this.id = this.generateClientId();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.companyType = builder.companyType;
        this.capitalStock = builder.capitalStock;
        this.auctionsAttended = 0;
        this.auctionsWon = 0;
        this.products = new ArrayList<>();
    }

    /**
     * Builder Class for Legal Person
     */
    public static class LegalPersonBuilder extends Client {
        private CompanyType companyType;
        private double capitalStock;

        public LegalPersonBuilder() {
            super();
        }

        public LegalPersonBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LegalPersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public LegalPersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public LegalPersonBuilder setCompanyType(String companyType) {
            this.companyType = CompanyType.valueOf(companyType.toUpperCase());
            return this;
        }

        public LegalPersonBuilder setCapitalStock(double capitalStock) throws NullOrNegativeCapitalStockException {
            if (capitalStock <= 0)
                throw new NullOrNegativeCapitalStockException("Company cannot have null or negative " +
                        "stock, stock capital is " + capitalStock);
            this.capitalStock = capitalStock;
            return this;
        }

        public LegalPerson build() {
            return new LegalPerson(this);
        }
    }

    @Override
    public String toString() {
        String printString = "Id: " + getId() +
                ", First Name: " + getFirstName() +
                ", Last Name: " + getLastName() +
                ", Address: " + getAddress() +
                ", Company Type: " + companyType +
                ", Capital Stock: " + capitalStock;
        if (attendedAuctions())
            printString += ", Auctions attended: " + getAuctionsAttended();
        if (wonAuctions())
            printString += ", Auctions won: " + getAuctionsWon();
        return printString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LegalPerson that = (LegalPerson) o;

        if (Double.compare(that.capitalStock, capitalStock) != 0) return false;
        return companyType == that.companyType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (companyType != null ? companyType.hashCode() : 0);
        temp = Double.doubleToLongBits(capitalStock);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
