package com.jokim.sivillage.api.product.vo.out;

import com.jokim.sivillage.api.hashtag.vo.HashtagResponseVo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponseVo {


    private String productCode;
    private String imageUrl;
    private String brandName;
    private String productName;
    private Integer discountRate;
    private Double amount;
    private Double price;
    private String starPoint;
    private Integer reviewCount;
    private List<HashtagResponseVo> hashTag;
    private String detail;


}
