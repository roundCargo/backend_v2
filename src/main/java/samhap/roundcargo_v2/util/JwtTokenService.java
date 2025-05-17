package samhap.roundcargo_v2.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final StringRedisTemplate redisTemplate;

    private static final Duration REFRESH_TTL = Duration.ofDays(14);
    private static final Duration ACCESS_TTL = Duration.ofHours(1);
    private static final Duration KAKAO_ACCESS_TTL = Duration.ofHours(6);
    private static final Duration KAKAO_REFRESH_TTL = Duration.ofDays(30);

    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set("jwt:refresh:" + userId, refreshToken, REFRESH_TTL);
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get("jwt:refresh:" + userId);
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete("jwt:refresh:" + userId);
    }

    public void blacklistAccessToken(String accessToken, long remainingTimeMillis) {
        redisTemplate.opsForValue().set(
                "jwt:blacklist:" + accessToken,
                "logout",
                Duration.ofMillis(remainingTimeMillis)
        );
    }

    public boolean isBlacklisted(String accessToken) {
        return redisTemplate.hasKey("jwt:blacklist:" + accessToken);
    }

    public void saveKakaoAccessToken(String kakaoId, String kakaoAccessToken) {
        redisTemplate.opsForValue().set("kakao:access:" + kakaoId, kakaoAccessToken, KAKAO_ACCESS_TTL);
    }

    public void saveKakaoRefreshToken(String kakaoId, String kakaoRefreshToken) {
        redisTemplate.opsForValue().set("kakao:refresh:" + kakaoId, kakaoRefreshToken, KAKAO_REFRESH_TTL);
    }

    public String getKakaoAccessToken(String kakaoId) {
        return redisTemplate.opsForValue().get("kakao:access:" + kakaoId);
    }

    public String getKakaoRefreshToken(String kakaoId) {
        return redisTemplate.opsForValue().get("kakao:refresh:" + kakaoId);
    }

    public void deleteKakaoTokens(String kakaoId) {
        redisTemplate.delete("kakao:access:" + kakaoId);
        redisTemplate.delete("kakao:refresh:" + kakaoId);
    }
}