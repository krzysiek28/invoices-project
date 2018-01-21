package com.io.invoices.invoiceshibernate.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);
        this.printStackTrace();
    }
}
