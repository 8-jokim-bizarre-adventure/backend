package com.jokim.sivillage.api.bridge.productcategorylist.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductCategoryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String productCode;

    @Column(nullable = false, length = 36)
    private String mainCategoryCode;

    @Column(length = 36)
    private String secondaryCategoyCode;

    @Column(length = 36)
    private String tertiaryCategoyCode;

    @Column(length = 36)
    private String quaternaryCategoyCode;

}
