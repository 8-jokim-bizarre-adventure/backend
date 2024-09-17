package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom {

    List<Product> findFilteredProduct(Long sizeId, Long colorId, Long etcId);

    ProductResponseDto findProductByProductCode(String productCode);
//    List<Product> findProductByHit();

//    List<Product> findProductByNewProduct();

//    List<Product> findProductByReview();
//
//    List<Product> findProductBySalesVolume();
//
//    List<Product> findProductByDiscountRate();
//
//    List<Product> findProductByHighPrice();
//
//    List<Product> findProductByWish();

}
