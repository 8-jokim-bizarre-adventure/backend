package com.jokim.sivillage.api.product.infrastructure;

import com.jokim.sivillage.api.product.domain.Product;
//import com.querydsl.jpa.impl.JPAQueryFactory;
import com.jokim.sivillage.api.product.domain.QProduct;
import com.jokim.sivillage.api.product.domain.QProductOption;
import com.jokim.sivillage.api.product.domain.option.QColor;
import com.jokim.sivillage.api.product.domain.option.QEtc;
import com.jokim.sivillage.api.product.domain.option.QSize;
import com.jokim.sivillage.api.product.dto.out.ProductResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findFilteredProduct(Long sizeId, Long colorId, Long etcId) {

        QProduct product = QProduct.product;
        QProductOption productOption = QProductOption.productOption;
        QSize size = QSize.size;
        QColor color = QColor.color;
        QEtc etc = QEtc.etc;

        log.info("productOption.size.id {}", productOption.size.id);

        return jpaQueryFactory.selectFrom(product)
            .join(productOption).on(product.productCode.eq(productOption.productCode))
            .where(productOption.size.id.eq(sizeId),
                productOption.color.id.eq(colorId),
                productOption.etc.id.eq(etcId))
            .fetch();
    }

    
}
