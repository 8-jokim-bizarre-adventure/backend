package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import lombok.Builder;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
public class ProductRequestDto {


    private String productName;
    private String brandName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;

    public Product toEntity(String productCode, String brandCode) {
        return Product.builder().
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            build();
    }

    @Builder
    public ProductRequestDto(String productName, String brandName,
        boolean isOnSale, String detail, Double standardPrice, Double discountPrice) {
        this.productName = productName;
        this.brandName = brandName;
        this.isOnSale = isOnSale;
        this.detail = detail;
        this.standardPrice = standardPrice;
        this.discountPrice = discountPrice;
    }

    public ProductRequestDto() {
    }

    public static ProductRequestDto toDto(ProductRequestVo productRequestVo) {
        // VO -> Dto 필드 그대로면 modelMapper 사용하자.
        ModelMapper modelMapper = new ModelMapper();
        ProductRequestDto productRequestDto = modelMapper.map(productRequestVo,
            ProductRequestDto.class);
        return productRequestDto;
    }


}
