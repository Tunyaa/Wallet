package com.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.DTO.WalletDTO;
import com.wallet.model.OperationType;
import com.wallet.model.WalletOperationRequest;
import com.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void shouldProcessWalletOperation() throws Exception {

        WalletOperationRequest request = new WalletOperationRequest(UUID.randomUUID(), OperationType.DEPOSIT, new BigDecimal(100));
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());

        verify(walletService, times(1)).processWalletOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
    }

    @Test
    void shouldGetWalletById() throws Exception {

        UUID walletId = UUID.randomUUID();
        WalletDTO wallet = new WalletDTO(walletId, new BigDecimal(1000));
        when(walletService.getWAlletById(walletId)).thenReturn(wallet);

        mockMvc.perform(get("/api/v1/wallet/{walletId}", walletId.toString()))
                .andExpect(status().isOk());

        verify(walletService, times(1)).getWAlletById(walletId);
    }  
}
