package com.jokim.sivillage.api.wishlist.productwishlist.presentation;

import com.jokim.sivillage.api.wishlist.productwishlist.application.ProductWishlistService;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistRequestDto;
import com.jokim.sivillage.api.wishlist.productwishlist.dto.ProductWishlistResponseDto;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.in.AddProductWishlistRequestVo;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetAllProductWishlistResponseVo;
import com.jokim.sivillage.api.wishlist.productwishlist.vo.out.GetProductWishlistStateResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wishlist")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/wishlist/product")
public class ProductWishlistController {

    private final ProductWishlistService productWishlistService;

    @Operation(summary = "상품 Wishlist 생성 API")
    @PostMapping
    public BaseResponse<Void> addProductWishlist(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddProductWishlistRequestVo addProductWishlistRequestVo) {

        productWishlistService.addProductWishlist(ProductWishlistRequestDto.toDto(
                addProductWishlistRequestVo, authorizationHeader.replace("Bearer ", "")));
        return new BaseResponse<>();
    }

    @Operation(summary = "상품 Wishlist 전체 조회 API")
    @GetMapping
    public BaseResponse<List<GetAllProductWishlistResponseVo>> getAllProductWishlists(
            @RequestHeader("Authorization") String authorizationHeader) {

        return new BaseResponse<>(productWishlistService.getAllProductWishlists(
                authorizationHeader.replace("Bearer ", ""))
                .stream().map(ProductWishlistResponseDto::toVoForProductCode).toList());
    }

    @Operation(summary = "상품 Wishlist 상태 조회")
    @GetMapping("/{productCode}")
    public BaseResponse<GetProductWishlistStateResponseVo> getProductWishlistState(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String productCode) {

        return new BaseResponse<>(productWishlistService.getProductWishlistState(
                authorizationHeader.replace("Bearer ", ""), productCode)
                .toVoForIsChecked());
    }

}
