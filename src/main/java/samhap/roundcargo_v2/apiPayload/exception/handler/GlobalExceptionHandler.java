package samhap.roundcargo_v2.apiPayload.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenHandler.class)
    public ResponseEntity<?> handleTokenHandler(TokenHandler ex) {
        // ❗스택 트레이스 없이 메시지만 출력
        log.warn("토큰 관련 오류 발생: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
