package com.jokim.sivillage.api.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class HashtagResponseVo {

    private Long hashtagId;
    private String value;

}
