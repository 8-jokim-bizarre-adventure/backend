package com.jokim.sivillage.api.customer.dto;

import com.jokim.sivillage.api.customer.vo.RefreshTokenRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RefreshTokenRequestDto {
    private String refreshToken;

    public static RefreshTokenRequestDto toDto(String refreshToken){
        return RefreshTokenRequestDto.builder()
            .refreshToken(refreshToken)
            .build();
    }
}
