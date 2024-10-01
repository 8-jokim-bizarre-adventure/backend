package com.jokim.sivillage.api.purchase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String purchaseCode;

    @Column(nullable = false, length = 30)
    private String brandName;

    @Column(nullable = false, length = 255)
    private String productName;

    @Column(nullable = false, length = 50)
    private String productOptionName;

    @Column(nullable = false)
    private Short quantity;

    @Column(nullable = false)
    private Double standardPrice;

    @Column(nullable = false)
    private Double discountPrice;

    @Column(nullable = false, length = 800)
    private String address;

    @Column(length = 20)
    private String couponCode;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime purchasedAt;

}
