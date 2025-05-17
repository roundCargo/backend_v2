package samhap.roundcargo_v2.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS4001", "접근 권한이 없습니다."),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "사용자를 찾을 수 없습니다."),
    MEMBER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "MEMBER4002", "이미 가입된 사용자입니다. 새로고침 해주세요"),
    MEMBER_ALREADY_WITHDRAW(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 탈퇴된 회원입니다."),

    KAKAO_REQ_MODIFIED(HttpStatus.BAD_REQUEST, "KAKAO4001", "수정된 요청입니다."),
    KAKAO_REQ_NOT_FOUND(HttpStatus.NOT_FOUND, "KAKAO4002", "요청을 찾을 수없습니다."),
    KAKAO_PROFILE_NOT_FOUND(HttpStatus.BAD_REQUEST, "KAKAO4003", "사용자 프로필을 찾을 수 없습니다."),

    KAKAO_RES_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "KAKAO5001", "카카오로그인 응답 오류"),
    KAKAO_VALID_ERROR(HttpStatus.BAD_REQUEST, "KAKAO5002", "사용자 요청 검증에 실패했습니다."),

    FACTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FACTORY4001", "해당 계정이 없습니다."),
    FACTORY_IS_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "FACTORY4002", "이미 존재하는 아이디 입니다."),
    PASSWORD_IS_NOT_DUPLICATED(HttpStatus.BAD_REQUEST, "FACTORY4003", "비밀번호와 비밀번호 확인이 서로 다릅니다."),
    FACTORY_ALREADY_WITHDRAW(HttpStatus.BAD_REQUEST, "FACTORY4004", "이미 탈퇴된 회원입니다."),

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH4001", "계정과 일치하는 토큰이 없습니다."),
    TOKEN_NOT_USED(HttpStatus.BAD_REQUEST, "AUTH4002", "유효하지 않은 토큰 형식입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH4003", "유효하지 않은 토큰입니다.")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
