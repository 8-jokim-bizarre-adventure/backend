package com.jokim.sivillage.api.purchase.dto.out;

import com.jokim.sivillage.api.purchase.domain.Purchase;
import com.jokim.sivillage.api.purchase.vo.out.GetPurchaseSheetResponseVo;
import java.time.format.DateTimeFormatter;
import lombok.Builder;

@Builder
public class PurchaseResponseDto {

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

    public static PurchaseResponseDto toDto(Purchase purchase, String progress) {
        return PurchaseResponseDto
            .builder()
            .purchaseCode(purchase.getPurchaseCode())
            .brandName(purchase.getBrandName())
            .productName(purchase.getProductName())
            .productOptionName(purchase.getProductOptionName())
            .quantity(purchase.getQuantity())
            .standardPrice(purchase.getStandardPrice())
            .discountPrice(purchase.getDiscountPrice())
            .address(purchase.getAddress())
            .couponCode(purchase.getCouponCode())
            .purchasedAt(purchase.getPurchasedAt().format(DateTimeFormatter.ofPattern(
                "yyyy년 MM월 dd일 HH시 mm분 ss초")))
            .deliveryState(progress)
            .build();
    }

    public GetPurchaseSheetResponseVo toVo() {
        return GetPurchaseSheetResponseVo.builder()
            .purchaseCode(purchaseCode)
            .brandName(brandName)
            .productName(productName)
            .productOptionName(productOptionName)
            .quantity(quantity)
            .standardPrice(standardPrice)
            .discountPrice(discountPrice)
            .address(address)
            .couponCode(couponCode)
            .purchasedAt(purchasedAt)
            .deliveryState(deliveryState)
            .build();
    }

}
