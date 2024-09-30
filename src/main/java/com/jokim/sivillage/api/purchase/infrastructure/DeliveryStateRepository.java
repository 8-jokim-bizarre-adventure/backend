package com.jokim.sivillage.api.purchase.infrastructure;

import com.jokim.sivillage.api.purchase.domain.DeliveryState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryStateRepository extends JpaRepository<DeliveryState, Long> {

}
