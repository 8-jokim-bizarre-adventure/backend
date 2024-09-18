package com.jokim.sivillage.api.product.domain;


import com.jokim.sivillage.api.product.dto.in.UpdateProductRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "productCode")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String productCode;
    @Column(nullable = false, length = 255)
    private String productName;
    @Column(nullable = true, columnDefinition = "boolean default true")
    private boolean isOnSale;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;
    @Column(nullable = false)
    private Double standardPrice;
    @Column(nullable = false)
    private Double discountPrice;


}
