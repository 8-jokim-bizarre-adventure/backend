package com.jokim.sivillage.api.customer.vo.out;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseVo {

    private String accessToken;
    private String refreshToken;

}