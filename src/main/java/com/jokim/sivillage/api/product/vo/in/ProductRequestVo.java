package com.jokim.sivillage.api.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ProductRequestVo {
    
    private String productName;
    private String brandName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;

}
