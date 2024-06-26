package com.wallet.service;

import com.wallet.model.OperationType;
import com.wallet.model.Wallet;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface WalletService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void processWalletOperation(UUID walletId, OperationType operationType, BigDecimal amount);

    public Wallet getWAlletById(UUID walletId);

}
