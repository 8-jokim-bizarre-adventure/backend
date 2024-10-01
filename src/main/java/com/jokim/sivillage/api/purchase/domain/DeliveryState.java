package com.jokim.sivillage.api.purchase.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DeliveryState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false, length = 36)
    private String purchaseCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Progress progress;


    public static DeliveryState toEntity(String uuid, String purchaseCode) {
        return DeliveryState.builder()
            .uuid(uuid)
            .purchaseCode(purchaseCode)
            .progress(Progress.PAYMENT_COMPLETED)
            .build();
    }

}
