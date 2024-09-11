package com.jokim.sivillage.api.customer.dto.in;

import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.vo.in.SignInRequestVo;
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
public class SignInRequestDto {

    private String email;
    private String password;

    @Builder
    public static SignInRequestDto toDto(SignInRequestVo signInRequestVo){
        return SignInRequestDto.builder()
            .email(signInRequestVo.getEmail())
            .password(signInRequestVo.getPassword())
            .build();
    }

}