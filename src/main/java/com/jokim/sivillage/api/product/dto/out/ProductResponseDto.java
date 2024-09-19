package com.jokim.sivillage.api.product.dto.out;

import com.jokim.sivillage.api.hashtag.vo.HashtagResponseVo;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private String productCode;
    private String imageUrl;
    private String brandName;
    private String productName;
    private Integer discountRate;
    private Double amount;
    private Double price;
    private Double starPoint;
    private Long reviewCount;
    private List<HashtagResponseVo> hashTag;
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
