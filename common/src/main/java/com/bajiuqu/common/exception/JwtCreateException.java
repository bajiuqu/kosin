package com.bajiuqu.common.exception;

import com.bajiuqu.common.enums.ErrorCode;

/**
 * @author tanghc
 */
public class JwtCreateException extends RuntimeException {

    public ErrorCode getCode() {
        return ErrorCode.JWT_CREATE;
    }
}
