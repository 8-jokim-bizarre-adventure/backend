package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.vo.in.LogoutRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequestDto {

    private String accessToken;

    public static LogoutRequestDto toDto(LogoutRequestVo logoutRequestVo){
        return LogoutRequestDto.builder()
            .accessToken(logoutRequestVo.getAccessToken())
            .build();
    }


}
