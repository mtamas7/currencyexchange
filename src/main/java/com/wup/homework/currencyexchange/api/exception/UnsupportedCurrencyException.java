package com.wup.homework.currencyexchange.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "The requested currency is not supported!")
public class UnsupportedCurrencyException extends RuntimeException {
}
