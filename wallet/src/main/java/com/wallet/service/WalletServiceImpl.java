
package com.wallet.service;

import com.wallet.DTO.WalletDTO;
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

@Service 
@Data
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;
    private final WalletOperationRepository walletOperationRepository;

    @Override
    public WalletDTO processWalletOperation(UUID walletId, OperationType operationType, BigDecimal amount) {

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
        return new WalletDTO(walletId, wallet.getBalance());
    }

    @Override
    public WalletDTO getWAlletById(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
         return new WalletDTO(walletId, wallet.getBalance());
    }
}
