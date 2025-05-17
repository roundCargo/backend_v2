package samhap.roundcargo_v2.apiPayload.exception.handler;

import samhap.roundcargo_v2.apiPayload.code.BaseErrorCode;
import samhap.roundcargo_v2.apiPayload.exception.GeneralException;

public class FactoryHandler extends GeneralException {
    public FactoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
