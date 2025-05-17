package samhap.roundcargo_v2.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import samhap.roundcargo_v2.domain.common.model.Account;
import samhap.roundcargo_v2.domain.common.model.Enum.Role;
import samhap.roundcargo_v2.domain.common.model.Enum.Status;
import samhap.roundcargo_v2.domain.common.repository.AccountRepository;
import samhap.roundcargo_v2.domain.user.model.Car;
import samhap.roundcargo_v2.domain.user.model.User;
import samhap.roundcargo_v2.domain.user.repository.CarRepository;
import samhap.roundcargo_v2.domain.user.repository.UserRepository;
import samhap.roundcargo_v2.util.JwtProvider;
import samhap.roundcargo_v2.util.JwtTokenService;
import samhap.roundcargo_v2.util.KakaoOAuthClient;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final KakaoOAuthClient kakaoOAuthClient;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtTokenService jwtTokenService;
    private final AccountRepository accountRepository;
    private final CarRepository carRepository;

    public String kakaoLogin(String code) {
        String accessToken = kakaoOAuthClient.getAccessToken(code);
        Map<String, Object> userInfo = kakaoOAuthClient.getUserInfo(accessToken);

        Long kakaoId = Long.valueOf(userInfo.get("id").toString());
        String name = (String) userInfo.get("name");
        String phone = (String) userInfo.get("phoneNumber");

        User user = userRepository.findById(kakaoId)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .id(kakaoId)
                                .name(name)
                                .phoneNumber(phone)
                                .role(Role.USER)
                                .status(Status.비활성)
                                .build()
                ));

        String refreshToken = jwtProvider.createRefreshToken(kakaoId, Role.USER.name());
        jwtTokenService.saveRefreshToken(kakaoId.toString(), refreshToken);

        return jwtProvider.createAccessToken(kakaoId, Role.USER.name());
    }

    public void completeRegistration(Long kakaoId, UserRegistrationRequest request) {
        User user = userRepository.findById(kakaoId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new RuntimeException("계좌 없음"));
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("차량 없음"));

        user.setBusinessNumber(request.getBusinessNumber());
        user.setAccount(account);
        user.setCar(car);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
    }

    @Override
    public void kakaoLogout(String accessToken, String refreshToken) {
        accessToken = accessToken.replace("Bearer ", "");

        if (jwtProvider.validateToken(accessToken)) {
            long expiration = jwtProvider.getExpiration(accessToken);
            long now = System.currentTimeMillis();
            jwtTokenService.blacklistAccessToken(accessToken, expiration - now);
        }

        Long kakaoId = jwtProvider.getId(refreshToken);
        jwtTokenService.deleteRefreshToken(kakaoId.toString());
    }

    public void kakaoUnlink(String kakaoAccessToken) {
        Long kakaoId = kakaoOAuthClient.unlink(kakaoAccessToken);
        kakaoTokenService.deleteTokens(kakaoId);
        userRepository.findById(kakaoId).ifPresent(userRepository::delete);
    }

    @Override
    public String reissueAccessToken(String accessToken, String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 Refresh Token");
        }

        Long kakaoId = jwtProvider.getId(refreshToken);
        String storedRefresh = jwtTokenService.getRefreshToken(kakaoId.toString());

        if (!refreshToken.equals(storedRefresh)) {
            throw new RuntimeException("서버 저장 Refresh Token과 일치하지 않음");
        }

        return jwtProvider.createAccessToken(kakaoId, Role.USER.name());
    }
}

