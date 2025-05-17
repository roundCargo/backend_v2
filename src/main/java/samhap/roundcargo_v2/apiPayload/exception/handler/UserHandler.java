package samhap.roundcargo_v2.apiPayload.exception.handler;

import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
