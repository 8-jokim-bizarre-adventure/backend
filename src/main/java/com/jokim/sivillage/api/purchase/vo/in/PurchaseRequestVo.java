package com.jokim.sivillage.api.purchase.vo.in;

import lombok.Getter;

@Getter
public class PurchaseRequestVo {

    private String brandName;
    private String productName;
    private String productOptionName;
    private Short quantity;
    private Double standardPrice;
    private Double discountPrice;
    private String address;
    private String couponCode;

}
