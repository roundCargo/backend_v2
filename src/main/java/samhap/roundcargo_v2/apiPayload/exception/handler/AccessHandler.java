package samhap.roundcargo_v2.apiPayload.exception.handler;

import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.exception.GeneralException;

public class AccessHandler extends GeneralException {
    public AccessHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}