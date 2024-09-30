package com.jokim.sivillage.api.trending.mostviewproduct.infrastructure;

import com.jokim.sivillage.api.trending.mostviewproduct.domain.MostViewProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MostViewProductRepository extends JpaRepository<MostViewProduct, Long> {
    List<MostViewProduct> findAll();
}
