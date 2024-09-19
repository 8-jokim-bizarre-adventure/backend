package com.jokim.sivillage.api.product.dto.out;


import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ProductListResponseDto {

    private String productCode;
    private String productName;
    private String imageUrl;
    private String discountRate;
    private String brandName;


    public static ProductListResponseVo toResponseVo(ProductListResponseDto dto) {
        return ProductListResponseVo.builder()
            .productCode(dto.getProductCode())
            .productName(dto.getProductName())
            .imageUrl(dto.getImageUrl())
            .discountRate(dto.getDiscountRate())
            .brandName(dto.getBrandName())
            .build();
    }

}
