package com.hsbc.banking.controller;

import com.hsbc.banking.dto.TransactionDto;
import com.hsbc.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     *  Get all transactions
     */
    @GetMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions().stream()
             .map(transaction -> new TransactionDto(transaction.getId(), transaction.getDescription(), transaction.getAmount()))
             .toList();
    }

    /**
     * Get transactions by page
     */
    @GetMapping("/page")
    public List<TransactionDto> getTransactionsPage(@RequestParam int page, @RequestParam int size) {
        return transactionService.getTransactionsPage(page, size).stream()
                 .map(transaction -> new TransactionDto(transaction.getId(), transaction.getDescription(), transaction.getAmount()))
                 .toList();
    }
    
    /**
     * Create a transaction. 
     * Duplicate transactions will throw RuntimeException.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestParam String description, @RequestParam double amount) {
        try {
        	return new TransactionDto(transactionService.createTransaction(description, amount).getId(), description, amount);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }    
    }

    /**
     * Delete a transaction by ID.
     * Deleting a non-existent transaction will return false.
     */
    @DeleteMapping("/{id}")
    public boolean deleteTransaction(@PathVariable UUID id) {
        return transactionService.deleteTransaction(id);
    }

    @PutMapping("/{id}")
    public boolean modifyTransaction(@PathVariable UUID id, @RequestParam String description, @RequestParam double amount) {
        return transactionService.modifyTransaction(id, description, amount);
    }
}