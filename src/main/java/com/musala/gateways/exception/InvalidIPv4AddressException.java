package com.musala.gateways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "This gateway has an invalid IP Address")
public class InvalidIPv4AddressException extends Exception
{
    private static final long serialVersionUID = 100L;
}