package com.jokim.sivillage.api.bridge.productcategorylist.presentation;

import com.jokim.sivillage.api.bridge.productcategorylist.application.ProductCategoryListService;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListRequestDto;
import com.jokim.sivillage.api.bridge.productcategorylist.dto.ProductCategoryListResponseDto;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.ProductCategoryListRequestVo;
import com.jokim.sivillage.api.bridge.productcategorylist.vo.GetProductCategoryListResponseVo;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.utils.CursorPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product-Category")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-category")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @Operation(summary = "Product-Category-List 생성 API", description =
        "Add Product By Categories **(isOnSale 값 비우면 자동으로 true 들어감)**")
    @PostMapping
    public BaseResponse<Void> addProductByCategories(
            @RequestBody ProductCategoryListRequestVo productCategoryListRequestVo) {

        productCategoryListService.addProductByCategories(ProductCategoryListRequestDto.toDto(
            productCategoryListRequestVo));

        return new BaseResponse<>();
    }

    @Operation(summary = "Product-Category-List 조회 API", description = "카테고리별 상품 조회")
    @GetMapping
    public BaseResponse<CursorPage<GetProductCategoryListResponseVo>> getProductCategoryList(
            @RequestParam(value = "mainCategoryCode", required = false) String mainCategoryCode,
            @RequestParam(value = "secondaryCategoryCode", required = false) String secondaryCategoryCode,
            @RequestParam(value = "tertiaryCategoryCode", required = false) String tertiaryCategoryCode,
            @RequestParam(value = "quaternaryCategoryCode", required = false) String quaternaryCategoryCode,
            @RequestParam(value = "lastId", required = false) Long lastId,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNo", required = false) Integer pageNo
    ) {
        CursorPage<ProductCategoryListResponseDto> cursorPage = productCategoryListService.
            getProductCategoryListByCategories(mainCategoryCode, secondaryCategoryCode,
                tertiaryCategoryCode, quaternaryCategoryCode, lastId, pageSize, pageNo);

        return new BaseResponse<>(CursorPage.toCursorPage(cursorPage,
            cursorPage.getContent().stream().map(ProductCategoryListResponseDto::toVo).toList()));
    }

    @Operation(summary = "Product-Category-List 판매 상태 수정 API", description =
        "productCode & categoryCodes 조건 모두 같은 경우 찾아서 Update")
    @PutMapping
    public BaseResponse<Void> updateProductCategoryList(
        @RequestBody ProductCategoryListRequestVo productCategoryListRequestVo) {

        productCategoryListService.updateProductCategoryList(ProductCategoryListRequestDto.toDto(
            productCategoryListRequestVo));
        return new BaseResponse<>();
    }

}
