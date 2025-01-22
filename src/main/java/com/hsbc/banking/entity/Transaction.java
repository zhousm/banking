package com.hsbc.banking.entity;

import java.util.UUID;

//Represents a financial transaction
public class Transaction {
    private UUID id;
    private String description;
    private double amount;

    // Constructor to initialize a transaction
    public Transaction(String description, double amount) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}