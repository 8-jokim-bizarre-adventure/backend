package com.jokim.sivillage.api.purchase.infrastructure;

import com.jokim.sivillage.api.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    boolean existsByPurchaseCode(String purchaseCode);

}
