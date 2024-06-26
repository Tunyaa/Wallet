package com.wallet.service;

import com.wallet.DTO.WalletDTO;
import com.wallet.model.OperationType;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


public interface WalletService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public WalletDTO processWalletOperation(UUID walletId, OperationType operationType, BigDecimal amount);

    public WalletDTO getWAlletById(UUID walletId);

}
