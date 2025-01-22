package com.hsbc.banking.service;

import com.hsbc.banking.entity.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
    	// clear all data before each test
        transactionService.clearTransactions();
    }

    
    @Test
    public void testGetAllTransactions() {
        transactionService.createTransaction("Test Transaction 1", 100.0);
        transactionService.createTransaction("Test Transaction 2", 200.0);
        List<Transaction> transactions = transactionService.getAllTransactions();
        assertEquals(2, transactions.size());
    }
    
    @Test
    public void testCreateTransaction() {
        Transaction transaction = transactionService.createTransaction("Test Transaction", 100.0);
        assertNotNull(transaction.getId());
        assertEquals("Test Transaction", transaction.getDescription());
        assertEquals(100.0, transaction.getAmount());
    }

    @Test
    public void testDeleteTransaction() {
        Transaction transaction = transactionService.createTransaction("Test Transaction", 100.0);
        assertTrue(transactionService.deleteTransaction(transaction.getId()));
        assertFalse(transactionService.deleteTransaction(UUID.randomUUID()));
    }

    @Test
    public void testModifyTransaction() {
        Transaction transaction = transactionService.createTransaction("Test Transaction", 100.0);
        assertTrue(transactionService.modifyTransaction(transaction.getId(), "Modified Transaction", 200.0));
        assertFalse(transactionService.modifyTransaction(UUID.randomUUID(), "Modified Transaction", 200.0));
    }


}