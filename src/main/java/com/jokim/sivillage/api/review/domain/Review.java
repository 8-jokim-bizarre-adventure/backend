package com.jokim.sivillage.api.review.domain;

import com.jokim.sivillage.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String reviewCode;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Double starPoint;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean state;
}