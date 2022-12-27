package com.gdsc.side.api.exception.controlleradvice;

import com.gdsc.side.api.exception.errorcode.CustomErrorCode;
import com.gdsc.side.api.exception.ErrorMessage;
import com.gdsc.side.api.exception.type.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

/**
 * MethodArgumentNotValidException : 400, @Vaild 검증 에러
 * IllegalArgumentException : 400, 메소드가 잘못되었거나 부적합한 인자 전달
 *
 * UserNotFoundException("U003") : 404, 유저 없음
 * DailyNotFoundException("U004") : 404, 데일리 없음
 * DailyDatesNotFoundException("U005")
 * GoalNotFoundException("U006") : 404, 골 없음
 * DiaryNotFoundException("U007") : 404, 다이어리 없음
 *
 * NoHandlerFoundException("U008") : 404, 잘못된 uri 요청
 * METHOD_NOT_ALLOWED("U009") : 405, GET, POST 등 잘못된 메서드 요청
 * INTERNAL_SERVER_ERROR("U010") : 500, 서버 에러
 *
 */
@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400에러
    public ErrorMessage handleTokenRefreshException(MethodArgumentNotValidException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.MethodArgumentNotValidException.code(),
                new Date(),
                CustomErrorCode.MethodArgumentNotValidException.message(),
                request.getDescription(false));

    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400에러
    public ErrorMessage handleUnsupportedJwtException(IllegalArgumentException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.IllegalArgumentException.code(),
                new Date(),
                CustomErrorCode.IllegalArgumentException.message(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.UserNotFoundException.code(),
                new Date(),
                CustomErrorCode.UserNotFoundException.message(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = DailyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDailyNotFoundException(DailyNotFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.DailyNotFoundException.code(),
                new Date(),
                CustomErrorCode.DailyNotFoundException.message(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = DailyDatesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDailyDatesNotFoundException(DailyDatesNotFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.DailyDatesNotFoundException.code(),
                new Date(),
                CustomErrorCode.DailyDatesNotFoundException.message(),
                request.getDescription(false));
    }


    @ExceptionHandler(value = GoalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleGoalNotFoundException(GoalNotFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.GoalNotFoundException.code(),
                new Date(),
                CustomErrorCode.GoalNotFoundException.message(),
                request.getDescription(false));
    }



    @ExceptionHandler(value = DiaryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleDiaryNotFoundException(DiaryNotFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.DiaryNotFoundException.code(),
                new Date(),
                CustomErrorCode.DiaryNotFoundException.message(),
                request.getDescription(false));
    }



    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUriNotFoundException(NoHandlerFoundException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.NoHandlerFoundException.code(),
                new Date(),
                CustomErrorCode.NoHandlerFoundException.message(),
                request.getDescription(false));
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage handleMethodNotAllowedError(HttpRequestMethodNotSupportedException ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.METHOD_NOT_ALLOWED.code(),
                new Date(),
                CustomErrorCode.METHOD_NOT_ALLOWED.message(),
                request.getDescription(false));
    }


    @ExceptionHandler(value = InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleInternalServerError(InternalServerError ex, WebRequest request) {

        return new ErrorMessage(
                CustomErrorCode.INTERNAL_SERVER_ERROR.code(),
                new Date(),
                CustomErrorCode.INTERNAL_SERVER_ERROR.message(),
                request.getDescription(false));
    }


}
