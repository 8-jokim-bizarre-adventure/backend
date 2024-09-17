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
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = "productCode")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 36)
    private String productCode;
    @Column(nullable = false, length = 255)
    private String productName;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isOnSale;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String detail;
    @Column(nullable = false)
    private Double standardPrice;

    @Builder
    public Product(String productCode, String productName, boolean isOnSale, String detail,
        Double standardPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.isOnSale = isOnSale;
        this.detail = detail;
        this.standardPrice = standardPrice;
    }

//    public void update(UpdateProductRequestDto updateProductRequestDto) {
////        this.brandCode = updateProductRequestDto.get
//    }
}
