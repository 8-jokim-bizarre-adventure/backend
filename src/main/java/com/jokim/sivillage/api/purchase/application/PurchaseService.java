package com.jokim.sivillage.api.purchase.application;

import com.jokim.sivillage.api.purchase.dto.PurchaseRequestDto;

public interface PurchaseService {

    void purchaseProduct(PurchaseRequestDto purchaseRequestDto);

}
