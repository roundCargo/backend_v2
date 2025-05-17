package samhap.roundcargo_v2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class KakaoTokenService {

    private final StringRedisTemplate redisTemplate;

    private static final Duration ACCESS_TTL = Duration.ofHours(6);     // 카카오 access token 유효시간
    private static final Duration REFRESH_TTL = Duration.ofDays(30);    // refresh token 유효시간

    public void saveTokens(String kakaoId, String accessToken, String refreshToken) {
        redisTemplate.opsForValue().set("kakao:access:" + kakaoId, accessToken, ACCESS_TTL);
        redisTemplate.opsForValue().set("kakao:refresh:" + kakaoId, refreshToken, REFRESH_TTL);
    }

    public String getAccessToken(String kakaoId) {
        return redisTemplate.opsForValue().get("kakao:access:" + kakaoId);
    }

    public String getRefreshToken(String kakaoId) {
        return redisTemplate.opsForValue().get("kakao:refresh:" + kakaoId);
    }

    public void deleteTokens(String kakaoId) {
        redisTemplate.delete("kakao:access:" + kakaoId);
        redisTemplate.delete("kakao:refresh:" + kakaoId);
    }
}
