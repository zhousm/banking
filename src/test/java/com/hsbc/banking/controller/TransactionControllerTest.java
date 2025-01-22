package com.hsbc.banking.controller;

import com.hsbc.banking.entity.Transaction;
import com.hsbc.banking.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.UUID;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void testGetAllTransactions() throws Exception {
        Transaction transaction1 = new Transaction("Test Transaction 1", 100.0);
        Transaction transaction2 = new Transaction("Test Transaction 2", 200.0);
        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(transaction1, transaction2));
        mockMvc.perform(get("/transactions"))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$[0].description").value("Test Transaction 1"))
             .andExpect(jsonPath("$[1].description").value("Test Transaction 2"));
    }

    @Test
    public void testCreateTransaction() throws Exception {
        // 模拟 createTransaction 方法的返回值
        Transaction mockTransaction = new Transaction("Test Transaction", 100.0);
        when(transactionService.createTransaction(anyString(), anyDouble())).thenReturn(mockTransaction);
        mockMvc.perform(post("/transactions?description=Test Transaction&amount=100.0"))
            .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteTransaction() throws Exception {
        mockMvc.perform(delete("/transactions/{id}", UUID.randomUUID()))
            .andExpect(status().isOk());
    }

    @Test
    public void testModifyTransaction() throws Exception {
        mockMvc.perform(put("/transactions/{id}?description=Modified Transaction&amount=200.0", UUID.randomUUID()))
            .andExpect(status().isOk());
    }
}