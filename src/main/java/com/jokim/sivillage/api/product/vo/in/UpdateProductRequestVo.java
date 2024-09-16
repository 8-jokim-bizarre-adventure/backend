package com.jokim.sivillage.api.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UpdateProductRequestVo {

    // productCode로 객체 찾고 나머지 필드 수정한다.
    private String productCode;
    private String productName;
    private boolean isOnSale;
    private String detail;
    private Double standardPrice;

}
