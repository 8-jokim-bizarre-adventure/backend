package com.jokim.sivillage.api.purchase.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Progress {

    PAYMENT_COMPLETED("결제가 완료된 상태"),
    PREPARING_ITEM("상품이 준비 중인 상태"),
    READY_FOR_SHIPPING("배송 준비가 완료된 상태"),
    IN_TRANSIT("배송이 진행 중인 상태"),
    DELIVERED("배송이 완료된 상태");

    private final String progress;

}
