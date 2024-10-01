package com.jokim.sivillage.api.purchase.dto.in;

import com.jokim.sivillage.api.purchase.domain.Purchase;
import com.jokim.sivillage.api.purchase.vo.in.PurchaseProductRequestVo;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseProductRequestDto {

    private String accessToken;
    private String brandName;
    private String productName;
    private String productOptionName;
    private Short quantity;
    private Double standardPrice;
    private Double discountPrice;
    private String address;
    private String couponCode;

    public static PurchaseProductRequestDto toDto(PurchaseProductRequestVo purchaseProductRequestVo, String accessToken) {
        return PurchaseProductRequestDto.builder()
            .accessToken(accessToken)
            .brandName(purchaseProductRequestVo.getBrandName())
            .productName(purchaseProductRequestVo.getProductName())
            .productOptionName(purchaseProductRequestVo.getProductOptionName())
            .quantity(purchaseProductRequestVo.getQuantity())
            .standardPrice(purchaseProductRequestVo.getStandardPrice())
            .discountPrice(purchaseProductRequestVo.getDiscountPrice())
            .address(purchaseProductRequestVo.getAddress())
            .couponCode(purchaseProductRequestVo.getCouponCode())
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
