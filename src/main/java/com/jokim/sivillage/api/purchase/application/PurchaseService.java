package com.jokim.sivillage.api.purchase.application;

import com.jokim.sivillage.api.purchase.dto.in.PurchaseProductRequestDto;
import com.jokim.sivillage.api.purchase.dto.out.PurchaseResponseDto;
import java.util.List;

public interface PurchaseService {

    void purchaseProduct(PurchaseProductRequestDto purchaseProductRequestDto);

    List<PurchaseResponseDto> getPurchaseSheet(String accessToken);

}
