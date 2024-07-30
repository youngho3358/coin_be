package com.coin.coininvestment.handler;

import com.coin.coininvestment.dto.ErrorDto;
import com.coin.coininvestment.exception.join.ExistEmailException;
import com.coin.coininvestment.exception.join.ExistNicknameException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
public class CustomExceptionHandler {
    //이미 존재하는 이메일로 회원가입을 시도할 때 발생한다
    @ResponseStatus(CONFLICT)
    @ExceptionHandler(value = { ExistEmailException.class, ExistNicknameException.class })
    @ResponseBody
    private ErrorDto conflict(RuntimeException ex, WebRequest request) {
        return new ErrorDto(CONFLICT.value(), ex.getMessage());
    }
}
