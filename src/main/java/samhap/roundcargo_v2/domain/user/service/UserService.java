package samhap.roundcargo_v2.domain.user.service;

public interface UserService {

    String kakaoLogin(String code);

    void kakaoLogout(String accessToken, String refreshToken);

    void kakaoUnlink(String kakaoAccessToken);

    String reissueAccessToken(String accessToken, String refreshToken);
}
