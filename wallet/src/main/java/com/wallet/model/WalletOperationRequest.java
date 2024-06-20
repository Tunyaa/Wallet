
package com.wallet.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class WalletOperationRequest {
    private UUID walletId;
    private OperationType operationType;
    private BigDecimal amount;
}
