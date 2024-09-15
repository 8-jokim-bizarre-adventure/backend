package com.jokim.sivillage.api.product.domain;


import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String productCode;
    @Column(nullable = false, length = 36)
    private String brandCode;
    @Column(nullable = false, length = 255)
    private String productName;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isOnSale;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;
    @Column(nullable = false)
    private Double standardPrice;

//    public void update(UpdateProductRequestDto updateProductRequestDto) {
////        this.brandCode = updateProductRequestDto.get
//    }
}
