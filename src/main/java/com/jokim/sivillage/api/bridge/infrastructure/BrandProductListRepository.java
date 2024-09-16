package com.jokim.sivillage.api.bridge.infrastructure;

import com.jokim.sivillage.api.bridge.domain.BrandProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandProductListRepository extends JpaRepository<BrandProductList, Long> {

    BrandProductList findBrandProductListByProductCode(String productCode);

}
