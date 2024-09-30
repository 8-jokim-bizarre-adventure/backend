package com.jokim.sivillage.api.purchase.presentation;

import static com.jokim.sivillage.common.utils.TokenExtractor.extractToken;

import com.jokim.sivillage.api.purchase.application.PurchaseService;
import com.jokim.sivillage.api.purchase.dto.PurchaseRequestDto;
import com.jokim.sivillage.api.purchase.dto.PurchaseResponseDto;
import com.jokim.sivillage.api.purchase.vo.in.PurchaseRequestVo;
import com.jokim.sivillage.api.purchase.vo.out.GetPurchaseSheetResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Purchase")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Operation(summary = "상품 주문 API")
    @PostMapping
    public BaseResponse<Void> purchaseProduct(
        @RequestHeader("Authorization") String authorizationHeader,
        @RequestBody PurchaseRequestVo purchaseRequestVo) {

        purchaseService.purchaseProduct(PurchaseRequestDto.toDto(purchaseRequestVo,
            extractToken(authorizationHeader)));
        return new BaseResponse<>();
    }

    @Operation(summary = "주문서 조회 API")
    @GetMapping
    public BaseResponse<List<GetPurchaseSheetResponseVo>> getPurchaseSheet(
        @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(purchaseService.getPurchaseSheet(
            extractToken(authorizationHeader)).stream().map(PurchaseResponseDto::toVo).toList());
    }

}
