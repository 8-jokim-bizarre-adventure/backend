package com.jokim.sivillage.api.product.vo.out;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductListResponseVo {

    private String productCode;
    private String productName;
    private String imageUrl;
    private String discountRate;
    private String brandName;
    @JsonProperty("productWishListId")
    private boolean isWish;


}
