package com.wallet.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletOperationRequest {

    private UUID walletId;

    private OperationType operationType;

    private BigDecimal amount;
}
