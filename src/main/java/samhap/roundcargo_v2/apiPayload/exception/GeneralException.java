package samhap.roundcargo_v2.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
