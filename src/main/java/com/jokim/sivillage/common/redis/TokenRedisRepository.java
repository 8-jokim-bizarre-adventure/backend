package com.jokim.sivillage.common.redis;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TokenRedisRepository extends CrudRepository<TokenRedis, String> {

    TokenRedis findByAccessToken(String accessToken); // AccessToken으로 찾아내기
    Optional<TokenRedis> findByRefreshToken(String refreshToken);
    void deleteByAccessToken(String accessToken);
    void deleteByRefreshToken(String refreshToken);

}
