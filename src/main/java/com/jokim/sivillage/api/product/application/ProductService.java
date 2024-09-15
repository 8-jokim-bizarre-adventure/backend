package com.jokim.sivillage.api.product.application;

import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import java.util.List;


public interface ProductService {

    ProductResponseDto getProductByProductCode(String productCode);

    void saveProduct(ProductRequestDto productRequestDto);

    void updateProduct(String productCode, UpdateProductRequestDto productRequestDto);

    List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId);

    List<ProductResponseDto> getProductsByCategory(Long categoryId);
}


