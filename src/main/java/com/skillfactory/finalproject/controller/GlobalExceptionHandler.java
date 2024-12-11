package com.skillfactory.finalproject.controller;

import com.skillfactory.finalproject.dto.ResponceExceptionDto;
import com.skillfactory.finalproject.exception.AccountNotFoundException;
import com.skillfactory.finalproject.exception.DepositIsNegativeException;
import com.skillfactory.finalproject.exception.MoneyNotEnoughException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AccountNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponceExceptionDto> handlerException(AccountNotFoundException e) {
        ResponceExceptionDto dto = new ResponceExceptionDto(-1, "Account not found");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DepositIsNegativeException.class})
    @ResponseBody
    public ResponseEntity<ResponceExceptionDto> handlerException(DepositIsNegativeException e) {
        ResponceExceptionDto dto = new ResponceExceptionDto(0, "Amount must not be negative");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MoneyNotEnoughException.class})
    @ResponseBody
    public ResponseEntity<ResponceExceptionDto> handlerException(MoneyNotEnoughException e) {
        ResponceExceptionDto dto = new ResponceExceptionDto(0, "Amount is not enough");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }



}
