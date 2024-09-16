package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import com.jokim.sivillage.api.hashtag.infrastructure.HashtagRepository;
import com.jokim.sivillage.api.hashtag.infrastructure.ProductHashtagRepository;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.infrastructure.ProductRepository;
import com.jokim.sivillage.api.product.infrastructure.ProductRepositoryCustom;
import com.jokim.sivillage.api.product.vo.in.ProductRequestVo;
import com.jokim.sivillage.api.product.vo.in.UpdateProductRequestVo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ProductHashtagRepository productHashtagRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    @Override
    public ProductResponseDto getProductByProductCode(String productCode) {

        // == Product ==
        Product product = productRepository.findByProductCode(productCode)
            .orElseThrow(() -> new EntityNotFoundException(
                "Product not found with productCode: " + productCode));

        // brandName 얻기
//        String brandCode = product.getBrandCode();
//        Brand brand = brandRepository.findByBrandCode(brandCode)
//            .orElseThrow(() -> new EntityNotFoundException(
//                "Brand not found with brandCode: " + brandCode));
//        String brandName = brand.getMainName();
        String brandName = "temp";
        //todo productbrand 중개테이블 생기면 productCode로 접근하기

        // HashTagList 얻기
        List<Hashtag> hashtags = productHashtagRepository.findByProductCode(productCode)
            .orElseThrow(() -> new EntityNotFoundException(
                "Hashtag not found with productCode: " + productCode));

        List<Map<String, Object>> hashtagList = hashtags.stream().map(hashtag -> {
            Map<String, Object> ht = new HashMap<String, Object>();
            ht.put("hashtagId", hashtag.getId());
            ht.put("value", hashtag.getValue());
            return ht;
        }).toList();
        log.info("hashtagList: {}", hashtagList.toString());

        ModelMapper modelMapper = new ModelMapper();
        ProductResponseDto productResponseDto
            = modelMapper.map(product, ProductResponseDto.class);

        productResponseDto.setBrandName(brandName);
        productResponseDto.setHashTag(hashtagList);

        return productResponseDto;
    }

    @Override
    public void saveProduct(ProductRequestDto productRequestDto) {

        // === product ===
        // product Uuid 생성
        String productUuid;
        String productCode;

        // product Uuid 중복확인
        do {
            productUuid = UUID.randomUUID().toString();
            productCode = productUuid.substring(0, 8);
        }
        while (productRepository.findByProductCode(productCode).isPresent());

        // == brand == //
        // brand명 입력 받을 시, brand code 찾아서 입력할 필요 있음.
        String brandName = productRequestDto.getBrandName();
        log.info("brandName: {}", brandName);
        Brand brand = brandRepository.findByMainName(brandName).orElseGet(() -> new Brand());
        log.info("brand: {}", brand.toString());
        String brandCode = brand.getBrandCode();

        // 카테고리 이름이 존재하지 않을 시, 코드 생성
        if (brandCode == null) {
            String brandUuid = UUID.randomUUID().toString();
        }

        productRepository.save(productRequestDto.toEntity(productCode, brandCode));
    }

    @Override
    @Transactional
    public void updateProduct(String productCode,
        UpdateProductRequestDto updateproductRequestDto) {
        Product product = productRepository.findByProductCode(productCode).orElseThrow(
            () -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")
        );

        productRepository.save(updateproductRequestDto.toEntity(productCode, product.getId()));
    }

    // 옵션 별 상품 반환
    @Override
    public List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId) {
        log.info("before productRepository");
        List<Product> products = productRepositoryCustom.findFilteredProduct(sizeId, colorId,
            etcId);
        log.info("List<Product> products[0] {}", products.get(0).toString());
        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseDto> productResponseDtos = products.stream()
            .map(product -> modelMapper.map(product, ProductResponseDto.class))
            .collect(Collectors.toList());

        log.info("productResponseDtos {}", productResponseDtos);

        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Long categoryId) {

        return List.of();
    }

//    @Override
//    public void deleteProduct(long id) {
//        Product product = productRepository.findById(id).get();
//
//
//    }

//    @Override
//    public List<DailyHotProductResponseDto> getDailyHotProducts() {
//
//        return List.of();
//    }

//    @Override
//    public List<ProductResponseDto> getRandomProducts(Integer count) {
//        List<Product> products = productRepository.findRandomProducts(count);
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseDto> productResponseDtos = products.stream()
//            .map(product -> modelMapper.map(product, ProductResponseDto.class))
//            .collect(Collectors.toList());
//        return productResponseDtos;
//    }
//
//    @Override
//    public List<ProductResponseDto> getProductsBySortType(String sortType) {
//
//        return List.of();
//    }
}