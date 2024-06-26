
package com.wallet.DTO;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WalletDTO {
    private UUID id;
    private BigDecimal balance;

    public WalletDTO() {
    }
    
    
}
