package com.jokim.sivillage.api.hashtag.infrastructure;

import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHashtagRepositoryCustom {

    Optional<List<Hashtag>> findHashtagsByProductCode(String productCode);
}
