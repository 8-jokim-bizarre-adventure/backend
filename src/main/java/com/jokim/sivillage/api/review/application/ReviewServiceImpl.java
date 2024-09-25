package com.jokim.sivillage.api.review.application;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.jokim.sivillage.api.bridge.reviewmedialist.infrastructure.ReviewMediaListRepository;
import com.jokim.sivillage.api.review.domain.*;
import com.jokim.sivillage.api.review.dto.in.ReviewRequestDto;
import com.jokim.sivillage.api.review.dto.out.ReviewResponseDto;
import com.jokim.sivillage.api.review.dto.out.ReviewSummaryResponseDto;
import com.jokim.sivillage.api.review.infrastructure.*;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ProductEvaluationManageRepository productEvaluationManageRepository;
    private final EvaluationItemNameRepository evaluationItemNameRepository;
    private final EvaluationItemValueRepository evaluationItemValueRepository;
    private final ReviewMediaListRepository reviewMediaListRepository;
    private final ProductStatisticRepository productStatisticRepository;
    private final ProductStarAverageRepository productStarAverageRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void createReview(ReviewRequestDto reviewRequestDto) {
        // 여기 uuid 가져오는거 수정 필요할수도 예외처리가 안됨
        String uuid = "1";
        if (uuid == null) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            reviewRepository.save(reviewRequestDto.toEntity(uuid));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    //todo 이미지 보내는거 만들어둬야함
    @Override
    public ReviewSummaryResponseDto getReviewSummary(String productCode) {
        ProductStarAverage starAverage = productStarAverageRepository.findByProductCode(productCode);

        List<ReviewSummaryResponseDto.EvaluationSummary> evaluationSummaries =
            ReviewSummaryResponseDto.fetchEvaluationSummaries(jpaQueryFactory, productCode);

        return ReviewSummaryResponseDto.of(starAverage.getStarPoint(), evaluationSummaries);
    }

    //todo 이미지 보내는거 만들어둬야함
    @Override
    public Page<ReviewResponseDto> getReview(String productCode, Pageable pageable) {
        // Review와 EvaluationItemValue를 조인하여 isBest 값을 함께 가져오기
        Page<Review> reviews = reviewRepository.findByProductCodeOrderByIsBest(productCode, pageable);

        return reviews.map(review -> {
            // 해당 리뷰에 대한 평가 항목 가져오기
            List<ReviewResponseDto.Evaluation> evaluations = getEvaluations(review.getReviewCode());

            // EvaluationItemValue 테이블에서 isBest 값을 가져오기
            Boolean isBest = evaluationItemValueRepository.findByReviewCode(review.getReviewCode())
                .stream()
                .anyMatch(EvaluationItemValue::getIsBest);  // isBest가 true인 평가 항목이 있는지 확인

            // isBest 값을 포함하여 ReviewResponseDto로 변환
            return ReviewResponseDto.fromReview(review, evaluations, isBest);
        });
    }

    public List<ReviewResponseDto.Evaluation> getEvaluations(String reviewCode) {
        return evaluationItemValueRepository.findByReviewCode(reviewCode).stream()
            .map(value -> ReviewResponseDto.Evaluation.builder()
                .name(value.getEvaluationItemName().getName()) // 평가 항목 이름 가져오기
                .value(value.getValue()) // 평가 항목 값 가져오기
                .build())
            .toList();
    }

}
