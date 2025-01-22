package com.hsbc.banking.dto;

import java.util.UUID;

// Data Transfer Object for transactions
public class TransactionDto {
    private UUID id;
    private String description;
    private double amount;

    public TransactionDto(UUID id, String description, double amount) {
        this.id = id;
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
}