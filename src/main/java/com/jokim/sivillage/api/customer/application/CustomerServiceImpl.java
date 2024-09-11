package com.jokim.sivillage.api.customer.application;


import com.jokim.sivillage.api.customer.entity.AuthUserDetail;
import com.jokim.sivillage.common.entity.BaseResponseStatus;
import com.jokim.sivillage.common.exception.BaseException;
import com.jokim.sivillage.common.jwt.JwtTokenProvider;
import com.jokim.sivillage.common.redis.TokenRedis;
import com.jokim.sivillage.common.redis.TokenRedisRepository;
import com.jokim.sivillage.api.customer.domain.*;
import com.jokim.sivillage.api.customer.dto.in.*;
import com.jokim.sivillage.api.customer.dto.out.SignInResponseDto;
import com.jokim.sivillage.api.customer.infrastructure.*;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@ToString
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    //회원가입 시 필요한 repository
    private final CustomerRepository customerRepository;
    private final CustomerMarketingRepository customerMarketingRepository;
    private final CustomerPolicyRepository customerPolicyRepository;
    private final CustomerAdressRepository customerAdressRepository;
    private final CustomerDefaultAddresRepository customerDefaultAddresRepository;
    private final SocialCustomerRepository socialCustomerRepository;

    //로그인 시 필요한 repository
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //redis
    private final TokenRedisRepository tokenRedisRepository;


    //이메일 중복 체크
    @Override
    public Optional<Customer> findUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        //이메일 중복체크
        Customer customer = findUserByEmail(signUpRequestDto.getEmail()).orElse(null);
        if (customer != null) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }

        try{
            String uuid = UUID.randomUUID().toString();
            customerRepository.save(signUpRequestDto.toCustomerEntity(passwordEncoder, uuid, State.ACTIVATION));
            customerMarketingRepository.save(signUpRequestDto.toMarketingEntity(uuid));
            customerPolicyRepository.save(signUpRequestDto.toPolicyEntity(uuid));
        } catch (Exception e) {
            throw new IllegalArgumentException("회원가입 실패");
        }

    }

    @Override
    @Transactional
    public SignInResponseDto oauthSignIn(OauthSignInRequestDto oauthSignInRequestDto) {
        // 이메일로 회원 검색
        Customer customer = findUserByEmail(oauthSignInRequestDto.getEmail()).orElse(null);

        if (customer != null) {
            // 회원이 존재할 경우 소셜 계정 연결 체크
            Optional<SocialCustomer> socialCustomerOpt = socialCustomerRepository.findByUuidAndOauthProviderId(customer.getUuid(), oauthSignInRequestDto.getOauthProviderId());

            if (socialCustomerOpt.isEmpty()) {
                // 새로운 소셜 계정을 연결
                socialCustomerRepository.save(oauthSignInRequestDto.toEntity(customer.getUuid()));
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                customer, null, customer.getAuthorities());

            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            // Redis에 토큰 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getUuid(), refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.toDto(accessToken, refreshToken);

        }
            // 로그인 처리. null로 보내면 컨트롤러에서 if문 사용해 회원가입 먼저 하라고 오류 출력
        return null;
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        // 1. 이메일로 사용자 찾기
        Customer customer = customerRepository.findByEmail(signInRequestDto.getEmail())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN));

        try {
            // 2. 사용자 인증 (비밀번호 포함)
            Authentication authentication = authenticate(customer, signInRequestDto.getPassword());

            // 3. 토큰 생성
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            // 4. Redis에 Refresh Token 저장
            TokenRedis tokenRedis = new TokenRedis(customer.getUuid(), refreshToken);
            tokenRedisRepository.save(tokenRedis);

            return SignInResponseDto.toDto(accessToken, refreshToken);

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
    }



    //리프레시 토큰을 확인하여 accessToken 재발급
    @Override
    public SignInResponseDto refreshAccessToken(String refreshToken) {
        // Redis에서 refreshToken으로 사용자 정보 확인
        TokenRedis tokenRedis = tokenRedisRepository.findByRefreshToken(refreshToken);

        if (tokenRedis == null) {
            throw new IllegalArgumentException("유효하지 않은 RefreshToken입니다.");
        }

        // refreshToken이 유효하다면 새로운 accessToken 생성
        String customerUuid = tokenRedis.getId();

        // Customer 객체를 principal로 사용하는 Authentication 생성
        Customer customer = customerRepository.findByUuid(customerUuid)
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            customer, null, customer.getAuthorities());

        // 새로운 AccessToken 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

        // 새로운 AccessToken 반환
        return SignInResponseDto.toDto(newAccessToken,refreshToken);
    }

    @Override
    public void logout(String accessToken) {
        // accessToken을 Redis에서 삭제
        tokenRedisRepository.deleteByAccessToken(accessToken);
        log.info("로그아웃 완료: accessToken {}", accessToken);
    }

    private Authentication authenticate(Customer customer, String inputPassword) {
        AuthUserDetail authUserDetail = new AuthUserDetail(customer);
        return authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authUserDetail.getUsername(),
                inputPassword
            )
        );
    }



}
