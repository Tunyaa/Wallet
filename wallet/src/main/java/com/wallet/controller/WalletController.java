package com.wallet.controller;

import com.wallet.DTO.WalletDTO;
import com.wallet.model.OperationType;
import com.wallet.model.WalletOperationRequest;
import com.wallet.service.WalletService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public WalletDTO processWalletOperation(@RequestBody WalletOperationRequest request) throws Exception {

        if (!request.getWalletId().toString().matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {

            throw new BadRequestException();
        }
        if (request.getOperationType() != OperationType.DEPOSIT && request.getOperationType() != OperationType.WITHDRAW) {

            throw new BadRequestException();
        }
        if (request.getAmount().signum() < 0) {

            throw new BadRequestException();
        }
        return walletService.processWalletOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
    }

    @GetMapping("/{walletId}")
    public WalletDTO getWalletById(@PathVariable UUID walletId) {
        return walletService.getWAlletById(walletId);
    }
    
}
