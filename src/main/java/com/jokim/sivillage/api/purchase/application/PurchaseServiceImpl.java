package com.jokim.sivillage.api.purchase.application;

import static com.jokim.sivillage.api.purchase.domain.Progress.PAYMENT_COMPLETED;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.FAILED_TO_GENERATE_PURCHASE_CODE;
import static com.jokim.sivillage.common.entity.BaseResponseStatus.INVALID_PURCHASE_QUANTITY_OR_PRICE;

import com.jokim.sivillage.api.purchase.domain.DeliveryState;
import com.jokim.sivillage.api.purchase.domain.Purchase;
import com.jokim.sivillage.api.purchase.dto.in.PurchaseProductRequestDto;
import com.jokim.sivillage.api.purchase.dto.out.PurchaseResponseDto;
import com.jokim.sivillage.api.purchase.infrastructure.DeliveryStateRepository;
import com.jokim.sivillage.api.purchase.infrastructure.PurchaseRepository;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.utils.CodeGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final DeliveryStateRepository deliveryStateRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final int MAX_CODE_TRIES = 5;

    @Transactional
    @Override
    public void purchaseProduct(PurchaseProductRequestDto purchaseProductRequestDto) {

        if(purchaseProductRequestDto.getQuantity() <= 0 || purchaseProductRequestDto.getStandardPrice() < 0
            || purchaseProductRequestDto.getDiscountPrice() < 0) throw new BaseException(INVALID_PURCHASE_QUANTITY_OR_PRICE);

        String uuid = jwtTokenProvider.validateAndGetUserUuid(purchaseProductRequestDto.getAccessToken());
        String purchaseCode = generateUniquePurchaseCode();

        purchaseRepository.save(purchaseProductRequestDto.toEntity(uuid, purchaseCode));
        deliveryStateRepository.save(DeliveryState.toEntity(uuid, purchaseCode));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PurchaseResponseDto> getPurchaseSheet(String accessToken) {

        List<Purchase> purchaseList = purchaseRepository.findAllByUuid(jwtTokenProvider
            .validateAndGetUserUuid(accessToken));

        return purchaseList.stream().map(purchase -> {
            String progress = String.valueOf(deliveryStateRepository.findByPurchaseCode(purchase.getPurchaseCode())
                .map(DeliveryState::getProgress).orElse(PAYMENT_COMPLETED));

            return PurchaseResponseDto.toDto(purchase, progress);
        }).toList();
    }

    private String generateUniquePurchaseCode() {
        for(int i = 0; i < MAX_CODE_TRIES; i++) {
            String purchaseCode = CodeGenerator.generateCode("PH");

            if(!purchaseRepository.existsByPurchaseCode(purchaseCode)) return purchaseCode;
        }

        throw new BaseException(FAILED_TO_GENERATE_PURCHASE_CODE);
    }

}
