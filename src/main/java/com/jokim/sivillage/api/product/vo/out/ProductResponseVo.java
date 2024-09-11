package com.jokim.sivillage.api.product.vo.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseVo {

    @JsonProperty("productId")
    private Long id;
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


}
