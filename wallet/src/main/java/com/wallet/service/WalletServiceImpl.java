
package com.wallet.service;

import com.wallet.model.OperationType;
import static com.wallet.model.OperationType.DEPOSIT;
import static com.wallet.model.OperationType.WITHDRAW;
import com.wallet.model.Wallet;
import com.wallet.model.WalletOperation;
import com.wallet.repository.WalletOperationRepository;
import com.wallet.repository.WalletRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Data
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final WalletOperationRepository walletOperationRepository;

    @Override
    public void processWalletOperation(UUID walletId, OperationType operationType, BigDecimal amount) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        switch (operationType) {
            case DEPOSIT:
                wallet.setBalance(wallet.getBalance().add(amount));
                break;
            case WITHDRAW:
                if (wallet.getBalance().compareTo(amount) < 0) {
                    throw new RuntimeException("Not enough funds");
                }
                wallet.setBalance(wallet.getBalance().subtract(amount));
                break;
        }
        walletRepository.save(wallet);

        WalletOperation operation = new WalletOperation();
        operation.setWallet(walletId);
        operation.setOperationType(operationType);
        operation.setAmount(amount);
        operation.setTimestamp(LocalDateTime.now());
        walletOperationRepository.save(operation);
    }

    @Override
    public Wallet getWAlletById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
}
