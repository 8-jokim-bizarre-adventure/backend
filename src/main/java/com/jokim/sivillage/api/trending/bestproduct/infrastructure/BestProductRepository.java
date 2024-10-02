package com.jokim.sivillage.api.trending.bestproduct.infrastructure;

import com.jokim.sivillage.api.trending.bestproduct.domain.BestProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestProductRepository extends JpaRepository<BestProduct, Long> {
    List<BestProduct> findAll();
}
