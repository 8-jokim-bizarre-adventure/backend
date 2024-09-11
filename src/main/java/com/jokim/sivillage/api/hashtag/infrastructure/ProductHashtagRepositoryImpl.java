package com.jokim.sivillage.api.hashtag.infrastructure;

import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import com.jokim.sivillage.api.hashtag.domain.QProductHashtag;
import com.jokim.sivillage.api.product.infrastructure.ProductRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductHashtagRepositoryImpl implements ProductHashtagRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<List<Hashtag>> findHashtagsByProductCode(String productCode) {
        QProductHashtag productHashtag = QProductHashtag.productHashtag;

        List<Hashtag> hashtags = jpaQueryFactory
            .select(productHashtag.hashtag)
            .from(productHashtag)
            .where(productHashtag.productCode.eq(productCode))
            .fetch();

        return Optional.ofNullable(hashtags.isEmpty() ? null : hashtags);
    }
}
