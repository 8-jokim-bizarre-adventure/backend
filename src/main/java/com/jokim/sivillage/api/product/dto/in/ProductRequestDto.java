package com.jokim.sivillage.api.product.dto.in;

import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@ToString
public class ProductRequestDto {

    private String productCode;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;
    private Double discountPrice;

    public Product toEntity() {
        return Product.builder().
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            build();
    }

    public Product toEntity(Long productId) {
        return Product.builder().
            id(productId).
            productCode(productCode).
            productName(productName).
            isOnSale(isOnSale).
            detail(detail).
            standardPrice(standardPrice).
            discountPrice(discountPrice).
            build();
    }

    @Builder
    public ProductRequestDto(String productCode, String productName,
        boolean isOnSale, String detail, Double standardPrice, Double discountPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.isOnSale = isOnSale;
        this.detail = detail;
        this.standardPrice = standardPrice;
        this.discountPrice = discountPrice;
    }

    public ProductRequestDto() {
    }

    public static ProductRequestDto toDto(ProductRequestVo productRequestVo) {
        // VO -> Dto 필드 그대로면 modelMapper 사용하자.
        // null 값 담기는 오류
//        ModelMapper modelMapper = new ModelMapper();
//        ProductRequestDto productRequestDto = modelMapper.map(productRequestVo,
//            ProductRequestDto.class);
        return ProductRequestDto.builder()
            .productCode(productRequestVo.getProductCode())
            .productName(productRequestVo.getProductName())
            .isOnSale(productRequestVo.isOnSale())
            .detail(productRequestVo.getDetail())
            .standardPrice(productRequestVo.getStandardPrice())
            .discountPrice(productRequestVo.getDiscountPrice())
            .build();
    }


}
