package com.jokim.sivillage.api.customer.dto;

import com.jokim.sivillage.api.customer.vo.DuplicateEmailVo;
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
public class DuplicateEmailDto {
    private String email;

    public static DuplicateEmailDto toDto(DuplicateEmailVo duplicateEmailVo){
        return DuplicateEmailDto.builder()
            .email(duplicateEmailVo.getEmail())
            .build();
    }
}
