package samhap.roundcargo_v2.apiPayload.exception.handler;

import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.exception.GeneralException;

public class TokenHandler extends GeneralException {

    public TokenHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}