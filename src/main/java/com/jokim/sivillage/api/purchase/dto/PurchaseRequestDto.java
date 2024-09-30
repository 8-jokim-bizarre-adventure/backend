package com.jokim.sivillage.api.purchase.dto;

import com.jokim.sivillage.api.purchase.domain.Purchase;
import com.jokim.sivillage.api.purchase.vo.in.PurchaseRequestVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseRequestDto {

    private String accessToken;
    private String brandName;
    private String productName;
    private String productOptionName;
    private Short quantity;
    private Double standardPrice;
    private Double discountPrice;
    private String address;
    private String couponCode;

    public static PurchaseRequestDto toDto(PurchaseRequestVo purchaseRequestVo, String accessToken) {
        return PurchaseRequestDto.builder()
            .accessToken(accessToken)
            .brandName(purchaseRequestVo.getBrandName())
            .productName(purchaseRequestVo.getProductName())
            .productOptionName(purchaseRequestVo.getProductOptionName())
            .quantity(purchaseRequestVo.getQuantity())
            .standardPrice(purchaseRequestVo.getStandardPrice())
            .discountPrice(purchaseRequestVo.getDiscountPrice())
            .address(purchaseRequestVo.getAddress())
            .couponCode(purchaseRequestVo.getCouponCode())
            .build();
    }

    public Purchase toEntity(String uuid, String purchaseCode) {
        return Purchase.builder()
            .uuid(uuid)
            .purchaseCode(purchaseCode)
            .brandName(brandName)
            .productName(productName)
            .productOptionName(productOptionName)
            .quantity(quantity)
            .standardPrice(standardPrice)
            .discountPrice(discountPrice)
            .address(address)
            .couponCode(couponCode)
            .purchasedAt(LocalDateTime.now())
            .build();
    }

}
