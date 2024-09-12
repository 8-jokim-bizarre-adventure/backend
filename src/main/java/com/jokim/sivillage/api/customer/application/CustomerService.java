package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.dto.DuplicateEmailDto;
import com.jokim.sivillage.api.customer.dto.RefreshTokenResponseDto;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import java.util.Optional;
import org.springframework.security.core.Authentication;

public interface CustomerService {

    void signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto);

    RefreshTokenResponseDto refreshAccessToken(String refreshToken);

    void logout(String accessToken);

    Optional<Customer> findUserByEmail(String email);

    void duplicateEmail(DuplicateEmailDto duplicateEmailDto);
}
