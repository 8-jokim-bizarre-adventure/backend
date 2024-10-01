package com.jokim.sivillage.api.purchase.vo.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetPurchaseSheetResponseVo {

    private String purchaseCode;
    private String brandName;
    private String productName;
    private String productOptionName;
    private Short quantity;
    private Double standardPrice;
    private Double discountPrice;
    private String address;
    private String couponCode;
    private String purchasedAt;
    private String deliveryState;

}
