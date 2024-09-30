package com.jokim.sivillage.api.purchase.application;

import com.jokim.sivillage.api.purchase.dto.PurchaseRequestDto;
import com.jokim.sivillage.api.purchase.dto.PurchaseResponseDto;
import java.util.List;

public interface PurchaseService {

    void purchaseProduct(PurchaseRequestDto purchaseRequestDto);

    List<PurchaseResponseDto> getPurchaseSheet(String accessToken);

}
