package com.jokim.sivillage.api.product.application;


import com.jokim.sivillage.api.brand.domain.Brand;
import com.jokim.sivillage.api.brand.infrastructure.BrandRepository;
import com.jokim.sivillage.api.product.dto.in.ProductRequestDto;
import com.jokim.sivillage.api.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.api.product.domain.Product;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.api.product.infrastructure.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Override
    public ProductResponseDto getProductById(long id) {

        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductResponseDto.class);
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
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.map(productRequestDto, product);

        productRepository.save(product);

        return null;
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
//    public List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId) {
//        log.info("before productRepository");
//        List<Product> products = productRepository.findBySizeAndColorAndEtc(sizeId, colorId, etcId);
//        log.info("List<Product> products[0] {}", products.get(0).toString());
//        ModelMapper modelMapper = new ModelMapper();
//        List<ProductResponseDto> productResponseDtos = products.stream()
//            .map(product -> modelMapper.map(product, ProductResponseDto.class))
//            .collect(Collectors.toList());
//
//        log.info("productResponseDtos {}", productResponseDtos);
//
//        return productResponseDtos;
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