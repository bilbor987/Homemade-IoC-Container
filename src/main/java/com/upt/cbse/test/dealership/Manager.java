package com.upt.cbse.test.dealership;

public class Manager {

    private Person person;
    private Dealer dealer;

    public Manager() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String toString() {
        return "Manager{\n\tPerson: " + person + "\n\tDealer: " + dealer + "\n}";
    }
}
