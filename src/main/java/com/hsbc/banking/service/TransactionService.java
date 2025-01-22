package com.hsbc.banking.service;

import com.hsbc.banking.entity.Transaction;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Get all transactions
     */
    @Cacheable("transactions")
    public List<Transaction> getAllTransactions() {
        return transactions;
    }
    
    /**
     * Get transactions by page
     */
    public List<Transaction> getTransactionsPage(int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, transactions.size());
        return transactions.subList(start, end);
    }

    /**
     * Create a transaction.
     * Duplicate transactions will throw an `IllegalArgumentException`.
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public Transaction createTransaction(String description, double amount) {
        for (Transaction transaction : transactions) {
            if (transaction.getDescription().equals(description)) {
                throw new IllegalArgumentException("Duplicate transaction description");
            }
        }
    	Transaction transaction = new Transaction(description, amount);
        transactions.add(transaction);
        return transaction;
    }

    /**
     * Delete a transaction by ID. 
     * Deleting a non-existent transaction will return false.
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public boolean deleteTransaction(UUID id) {
        return transactions.removeIf(transaction -> transaction.getId().equals(id));
    }

    
    /**
     * Modify a transaction by ID
     */
    @CacheEvict(value = "transactions", allEntries = true)
    public boolean modifyTransaction(UUID id, String description, double amount) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(id)) {
                transaction.setDescription(description);
                transaction.setAmount(amount);
                return true;
            }
        }
        return false;
    }
    
    /**
     * clear all transactions 
     */
    public void clearTransactions() {
        transactions.clear();
    } 
}