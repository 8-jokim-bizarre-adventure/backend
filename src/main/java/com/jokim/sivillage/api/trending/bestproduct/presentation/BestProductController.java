package com.jokim.sivillage.api.trending.bestproduct.presentation;


import com.jokim.sivillage.api.trending.bestproduct.application.BestProductService;
import com.jokim.sivillage.api.trending.bestproduct.dto.BestProductResponseDto;
import com.jokim.sivillage.api.trending.bestproduct.vo.BestProductResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class BestProductController {

    private final BestProductService bestProductService;

    @Operation(summary = "Statistics API", description = "BestProduct API 입니다.", tags = {"Statistics"})
    @GetMapping("/best/popular-product")
    public BaseResponse<List<BestProductResponseVo>> getBestProduct() {
        return new BaseResponse<>(bestProductService.getBestProduct().stream().map(
            BestProductResponseDto::toVo).toList());
    }

}
