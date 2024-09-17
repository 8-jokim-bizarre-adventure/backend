package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;

import com.jokim.sivillage.api.bridge.domain.BrandProductList;
import com.jokim.sivillage.api.bridge.infrastructure.BrandProductListRepository;
import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import com.jokim.sivillage.api.hashtag.domain.ProductHashtag;
import com.jokim.sivillage.api.hashtag.infrastructure.ProductHashtagRepository;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.infrastructure.ProductRepository;
import com.jokim.sivillage.api.product.infrastructure.ProductRepositoryCustom;
import com.jokim.sivillage.api.product.vo.out.HashtagResponseVo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final ProductHashtagRepository productHashtagRepository;
    private final ProductRepositoryCustom productRepositoryCustom;
    private final BrandProductListRepository brandProductListRepository;

    @Override
    public ProductResponseDto getProductByProductCode(String productCode) {
        // todo 여러번의 join이 필요하지만.. queryDsl로 안전하게 짜보기
        // == Product ==
        Product product = productRepository.findByProductCode(productCode)
            .orElseThrow(() -> new EntityNotFoundException(
                "Product not found with productCode: " + productCode));

        // brandName 얻기
        // 1. brandCode 얻기
        BrandProductList brandProductList =
            brandProductListRepository.findBrandProductListByProductCode(productCode);
        if (brandProductList == null) {
            //todo
        }
        String brandCode = brandProductList.getBrandCode();

        // 2. 얻은 brandCode로 brandName 얻기
        Brand brand = brandRepository.findByBrandCode(brandCode)
            .orElseThrow(() -> new EntityNotFoundException(
                "Brand not found with brandCode: " + brandCode
            ));
        String brandName = brand.getMainName();
        log.info("brandName: {} " + brandName);

        // === Hashtag ===
        // ProductHashtag list 얻기
        List<ProductHashtag> productHashtags = productHashtagRepository.findByProductCode(
                productCode)
            .orElseThrow(() -> new EntityNotFoundException(
                "Hashtag not found with productCode: " + productCode));
        // Hashtag list 얻기
        List<Hashtag> hashtags = productHashtags.stream().map(ProductHashtag::getHashtag).toList();

        List<HashtagResponseVo> hashtagResponseVos = hashtags.stream().map(hashtag ->
            HashtagResponseVo.builder()
                .hashtagId(hashtag.getId())
                .value(hashtag.getValue())
                .build()
        ).toList();
        log.info("hashtagResponseVos: {}", hashtagResponseVos.toString());

        ModelMapper modelMapper = new ModelMapper();
        ProductResponseDto productResponseDto
            = modelMapper.map(product, ProductResponseDto.class);

        productResponseDto.setBrandName(brandName);
        productResponseDto.setHashTag(hashtagResponseVos);

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
            brandCode = brandUuid.substring(0, 8);
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

    // category별 product => 작성 중 정지
//    @Override
//    public List<ProductListResponseVo> getProductsByCategory(Long categoryId) {
//        // categoryId 에 해당하는 productCategoryList entity 반환
//        List<ProductCategoryList> productCategoryLists = productCategoryListRepository.findById(
//            categoryId);
//        if (productCategoryLists.isEmpty()) {
//            return new ArrayList<>();
//        }
//        // categoryId 에 해당하는 productCodes 반환하기
//        List<String> productCodes = productCategoryLists.stream()
//            .map(ProductCategoryList::getProductCode).toList();
//
//        // ProductCode에 해당하는 product 가져오기
//        List<Product> products = productCodes.stream().
//            map(productCode -> productRepository.findByProductCode(productCode).get()).toList();
//
//        // ProductListResponseVo로 만들기
//        List<ProductListResponseVo> productListResponseVos = products.stream().map(
//            product ->
//                ProductListResponseVo.builder()
//                    .productCode(product.getProductCode())
//                    .productName(product.getProductName())
//                    .imageUrl(
//                        "https://image.sivillage.com/upload/C00001/goods/org/293/220802002890293.jpg?RS=750&SP=1")
//                    // productMediaListRepository.findByProductCode().getMediaCode
//                    //todo 중개테이블 생성 후 확인
//                    // temp 값 입력
//                    .discountRate()
//                    .brandName()
//                    .isWish(false) // 초기값
//                    .build()
//
//        );
//
//        return List.of();
//    }

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