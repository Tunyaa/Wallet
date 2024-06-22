
package com.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "wallet")
@AllArgsConstructor
public class Wallet {
    @Id
    private UUID id;
    private BigDecimal balance;
}
