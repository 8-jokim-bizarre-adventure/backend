package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    private String productName;
    private String brandName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;

    public Product toEntity(String productCode, String brandCode) {
        return Product.builder().
            productCode(productCode).
            brandCode(brandCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            build();
    }

    @Builder
    public ProductRequestDto(String productName, String brandName,
        boolean isOnSale, String detail, Double standardPrice) {
        this.productName = productName;
        this.brandName = brandName;
        this.isOnSale = isOnSale;
        this.detail = detail;
        this.standardPrice = standardPrice;
    }


}
