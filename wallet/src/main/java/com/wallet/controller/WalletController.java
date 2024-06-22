package com.wallet.controller;

import com.wallet.model.Wallet;
import com.wallet.model.WalletOperationRequest;
import com.wallet.service.WalletService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/wallet")
@AllArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public void processWalletOperation(@RequestBody WalletOperationRequest request) {
        walletService.processWalletOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
    }

    @GetMapping("/{walletId}")
    public Wallet getWalletById(@PathVariable UUID walletId) {
        return walletService.getWAlletById(walletId);
    }
}
