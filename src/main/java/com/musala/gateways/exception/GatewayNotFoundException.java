package com.musala.gateways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "This gateway is not found in the system")
public class GatewayNotFoundException extends Exception
{
    private static final long serialVersionUID = 100L;
}
