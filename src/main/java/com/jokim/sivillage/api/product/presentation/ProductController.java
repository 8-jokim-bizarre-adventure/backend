package com.jokim.sivillage.api.product.presentation;

import com.jokim.sivillage.api.product.application.ProductService;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import com.jokim.sivillage.api.product.vo.in.UpdateProductRequestVo;
import com.jokim.sivillage.api.product.vo.out.ProductResponseVo;
import com.jokim.sivillage.common.entity.CommonResponseEntity;
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

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    // 상품 데이터 보기
    @GetMapping("/products/{productCode}")
    public ResponseEntity<ProductResponseVo> getProduct(@PathVariable String productCode) {
        log.info("productCoded : {}", productCode);
        ProductResponseDto productResponseDto = productService.getProductByProductCode(productCode);
        log.info("productResponseDto : {}", productResponseDto.toString());
        return ResponseEntity.ok(productResponseDto.toResponseVo());
    }

    // 상품 데이터 입력
    @PostMapping("/products")
    public CommonResponseEntity<Void> createProduct(
        @RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo : {}", productRequestVo.toString());
        ProductRequestDto productRequestDto = ProductRequestDto.builder()
            .productName(productRequestVo.getProductName())
            .brandName(productRequestVo.getBrandName())
            .isOnSale(true)
            .detail(productRequestVo.getDetail())
            .standardPrice(productRequestVo.getStandardPrice())
            .build();

        productService.saveProduct(productRequestDto);

        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "상품 등록 성공",
            null
        );
    }

    // 상품 업데이트 하기
    @PutMapping("/products")
    public CommonResponseEntity<Void> updateProduct(
        @RequestBody UpdateProductRequestVo updateProductRequestVo) {
        log.info("productRequestVo : {}", updateProductRequestVo.toString());

        UpdateProductRequestDto productRequestDto = UpdateProductRequestDto.builder()
            .productCode(updateProductRequestVo.getProductCode())
            .productName(updateProductRequestVo.getProductName())
            .brandName(updateProductRequestVo.getBrandName())
            .isOnSale(true)
            .detail(updateProductRequestVo.getDetail())
            .standardPrice(updateProductRequestVo.getStandardPrice())
            .build();

        productService.updateProduct(productRequestDto.getProductCode(), productRequestDto);

        return new CommonResponseEntity<>(
            HttpStatus.OK,
            "상품 변경 성공",
            null
        );
    }


    // 옵션 별  필터링 된 상품 보기
    @GetMapping("products/options")
    public ResponseEntity<List<ProductResponseVo>> getFilteredProduct(
        @RequestParam(value = "size-id") Long sizeId,
        @RequestParam(value = "color-id") Long colorId,
        @RequestParam(value = "etc-id") Long etcId) {
        log.info("productSize : {}", sizeId);
        log.info("productColor : {}", colorId);
        log.info("productEtc : {}", etcId);
        List<ProductResponseDto> productResponseDto = productService.getFilteredProducts(sizeId,
            colorId, etcId);

        log.info("productResponseDto : {}", productResponseDto.toString());
        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
//            .map(ResponseDto -> modelMapper.map(ResponseDto, ProductResponseVo.class)).collect(
//                Collectors.toList());
        List<ProductResponseVo> productResponseVo = productResponseDto.stream()
            .map(ProductResponseDto::toResponseVo).toList();
        log.info("productResponseVo : {}", productResponseVo.toString());
        return ResponseEntity.ok(productResponseVo);
    }

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
