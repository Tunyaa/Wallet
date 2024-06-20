
package com.wallet.repository;

import com.wallet.model.WalletOperation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletOperationRepository extends  JpaRepository<WalletOperation, Long>{
    
}
