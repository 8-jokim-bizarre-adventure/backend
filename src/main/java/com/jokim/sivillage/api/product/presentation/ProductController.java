package com.jokim.sivillage.api.product.presentation;

import com.jokim.sivillage.api.product.application.ProductService;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import com.jokim.sivillage.api.product.vo.in.UpdateProductRequestVo;
import com.jokim.sivillage.api.product.vo.out.ProductListResponseVo;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import com.jokim.sivillage.common.entity.BaseEntity;
import com.jokim.sivillage.common.entity.BaseResponse;
import com.jokim.sivillage.common.entity.CommonResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;


    // 상품 데이터 보기
    @Operation(summary = "상품 데이터 보기", description = "상품코드로 상품 데이터를 조회한다.")
    @GetMapping("/products/{productCode}")
    public BaseResponse<ProductResponseVo> getProduct(@PathVariable String productCode) {
        log.info("productCoded : {}", productCode);
        ProductResponseDto productResponseDto = productService.getProductByProductCode(productCode);
        log.info("productResponseDto : {}", productResponseDto.toString());
        ProductResponseVo productResponseVo = productResponseDto.toResponseVo();
        return new BaseResponse(productResponseVo);
    }

    // 상품 데이터 입력
    @PostMapping("/products")
    public BaseResponse<Void> createProduct(
        @RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo : {} in createProduct", productRequestVo.toString());
        ProductRequestDto productRequestDto = ProductRequestDto.toDto(productRequestVo);
        log.info("productRequestDto : in ProductController {}", productRequestDto.toString());
        productService.saveProduct(ProductRequestDto.toDto(productRequestVo));
        return new BaseResponse<>();
    }

    // 상품 업데이트 하기
    @Operation(summary = "상품 데이터 업데이트", description = "상품코드로 상품 데이터를 수정한다.")
    @PutMapping("/products")
    public BaseResponse<Void> updateProduct(
        @RequestBody UpdateProductRequestVo updateProductRequestVo) {
        log.info("productRequestVo : {}", updateProductRequestVo.toString());

        productService.updateProduct(updateProductRequestVo.getProductCode(),
            UpdateProductRequestDto.toDto(updateProductRequestVo));
        return new BaseResponse<>();
    }


    // 옵션 별  필터링 된 상품 보기
    @Operation
    @GetMapping("/products/options")
    public BaseResponse<List<ProductResponseVo>> getFilteredProduct(
        @RequestParam(value = "size-id") Long sizeId,
        @RequestParam(value = "color-id") Long colorId,
        @RequestParam(value = "etc-id") Long etcId) {
        log.info("productSize, color, etc id : {}, {}, {}", sizeId, colorId, etcId);
        List<ProductResponseDto> productResponseDto = productService.getFilteredProducts(sizeId,
            colorId, etcId);
        log.info("productResponseDto : {}", productResponseDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
            .map(ProductResponseDto::toResponseVo).toList();
        log.info("productResponseVo : {}", productResponseVo.toString());
        return new BaseResponse<>(productResponseVo);
    }

    //    //  전체상품보기(카테고리)
    // 개발 중 정지
//    @GetMapping("/products")
//    public BaseResponse<List<ProductListResponseVo>> getProductByCategory(
//        @RequestParam(value = "category-id") Long categoryId) {
//        log.info("categoryId : {}", categoryId);
//        List<ProductResponseDto> productResponseDto = productService.getProductsByCategory(
//            categoryId);
//
//        return new BaseResponse<>();
//
//    }

    // 랜덤 상품 리스트 보기
//    @GetMapping("/main/random-product")
//    public ResponseEntity<List<ProductResponseVo>> getRandomProduct(
//        @RequestParam(name = "count", required = false) Integer count) {
//        if (count == null) {
//            count = 5;
//        }
//        log.info("count : {}", count);
//
//        List<ProductResponseDto> productResponseDto = productService.getRandomProducts(count);
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
//            .map(ResponseDto -> modelMapper.map(ResponseDto, ProductResponseVo.class)).toList();
//        return ResponseEntity.ok(productResponseVo);
//    }

    // 정렬된 상품 보기
//    @GetMapping("/products/sort?sort-type={sortType}")
//    public ResponseEntity<List<ProductResponseVo>> getProductBySortType(
//        @RequestParam(name = "sort-type", required = false, defaultValue = "인기상품") String sortType) {
//        log.info("sortType : {}", sortType);
//        if (sortType.equals("인기상품")) {
//            productService.getProductsBySortType(sortType);
//        }
//
//        return ResponseEntity.ok(Collections.emptyList());
//    }


}
