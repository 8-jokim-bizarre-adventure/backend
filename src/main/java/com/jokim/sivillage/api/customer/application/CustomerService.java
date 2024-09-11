package com.jokim.sivillage.api.customer.application;

import com.jokim.sivillage.api.customer.domain.Customer;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import java.util.Optional;

public interface CustomerService {

    void signUp(SignUpRequestDto signUpRequestDto);

    SignInResponseDto signIn(SignInRequestDto signInRequestDto);

    SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto);

    SignInResponseDto refreshAccessToken(String refreshToken);

    void logout(String accessToken);

    Optional<Customer> findUserByEmail(String email);
}
