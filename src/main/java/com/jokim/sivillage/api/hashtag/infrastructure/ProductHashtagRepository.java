package com.jokim.sivillage.api.hashtag.infrastructure;

import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import com.jokim.sivillage.api.hashtag.domain.ProductHashtag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHashtagRepository extends JpaRepository<ProductHashtag, Long> {

    Optional<List<Hashtag>> findByProductCode(String productCode);

}
