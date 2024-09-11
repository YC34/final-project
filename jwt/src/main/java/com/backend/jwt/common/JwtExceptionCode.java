package com.backend.jwt.common;

public interface JwtExceptionCode {
    String UNKNOWN_ERROR ="NULL";
    String TOKEN_EXPIRED = "TE";
    String TOKEN_INVALID = "TI";
    String UNSUPPORTED_TOKEN = "UT";
}
