package com.poo.proiect.client;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Person extends Client {
    private final LocalDate birthday;

    private Person(PersonBuilder builder) {
        this.id = this.generateClientId();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.birthday = builder.birthday;
        this.auctionsAttended = 0;
        this.auctionsWon = 0;
        this.products = new ArrayList<>();
    }

    /**
     * Builder Class for Person
     */
    public static class PersonBuilder extends Client {
        private LocalDate birthday;

        public PersonBuilder() {
            super();
        }

        public PersonBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder setBirthday(String birthday) throws UnderageClientException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            this.birthday = LocalDate.parse(birthday, formatter);
            if (Period.between(this.birthday, LocalDate.now()).getYears() < 18)
                throw new UnderageClientException("Client must be at least 18 years old, birthday is: " + birthday);
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFormattedBirthday() {
        return getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Override
    public String toString() {
        String printString = "Id: " + getId() +
                ", First Name: " + getFirstName() +
                ", Last Name: " + getLastName() +
                ", Address: " + getAddress() +
                ", Birthday: " + getFormattedBirthday();
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

        Person person = (Person) o;

        return Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}