package com.algon.j2db.controller;

import com.algon.j2db.contract.BaseResponse;
import com.algon.j2db.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<BaseResponse<Object>> runtimeExceptionHandler(RequestException e) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.setRequestId(e.getRequestId());
        response.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
