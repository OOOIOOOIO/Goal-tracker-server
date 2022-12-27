package com.gdsc.side.api.exception.controlleradvice;

import com.gdsc.side.api.exception.ErrorMessage;
import com.gdsc.side.api.exception.errorcode.JwtCustomErrorCode;
import com.gdsc.side.api.exception.type.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * TokenRefreshException : 403
 *
 */

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403에러
    public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {

        // 여기서 body 보내고 흠흠
        return new ErrorMessage(
                JwtCustomErrorCode.TokenRefreshException.code(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }


}

