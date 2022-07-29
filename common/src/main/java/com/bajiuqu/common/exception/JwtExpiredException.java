package com.bajiuqu.common.exception;

/**
 * @author tanghc
 */
public class JwtExpiredException extends Exception {
    @Override
    public String getMessage() {
        return "jwt expired";
    }
}
