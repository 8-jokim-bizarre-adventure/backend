package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.vo.in.UpdateProductRequestVo;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
public class UpdateProductRequestDto {


    private String productCode;
    private String productName;
    private String brandName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;

    public Product toEntity(String brandCode, Long productId) {
        return Product.builder().
            id(productId).
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            build();
    }

    public Product toEntity() {
        return Product.builder().
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            build();
    }

    @Builder
    public UpdateProductRequestDto(String productCode, String productName, String brandName,
        boolean isOnSale, String detail, Double standardPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.brandName = brandName;
        this.isOnSale = isOnSale;
        this.detail = detail;
        this.standardPrice = standardPrice;
    }

    public static UpdateProductRequestDto toDto(UpdateProductRequestVo updateProductRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updateProductRequestVo, UpdateProductRequestDto.class);
    }


}

