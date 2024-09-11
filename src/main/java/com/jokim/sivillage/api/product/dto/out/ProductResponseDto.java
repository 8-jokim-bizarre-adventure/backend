package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class ProductResponseDto {

    private String productCode;
    private String imageUrl;
    private String brandName;
    private String productName;
    private String discountRate;
    private Double amount;
    private Double price;
    private String starPoint;
    private Integer reviewCount;
    private List<Map<String, Object>> hashTag;
    private String detail;

    public ProductResponseVo toResponseVo() {
        return ProductResponseVo.builder()
            .productCode(productCode)
            .imageUrl(imageUrl)
            .brandName(brandName)
            .productName(productName)
            .discountRate(discountRate)
            .amount(amount)
            .price(price)
            .starPoint(starPoint)
            .reviewCount(reviewCount)
            .hashTag(hashTag)
            .detail(detail)
            .build();
    }


}
