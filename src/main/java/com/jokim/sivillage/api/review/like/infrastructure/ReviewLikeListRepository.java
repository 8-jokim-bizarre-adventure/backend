package com.jokim.sivillage.api.review.like.infrastructure;

import com.jokim.sivillage.api.review.like.domain.ReviewLikeList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeListRepository extends JpaRepository<ReviewLikeList, Long> {

    Optional<ReviewLikeList> findByUuidAndReviewCode(String uuid, String reviewCode);

}
