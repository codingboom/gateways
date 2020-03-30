package com.musala.gateways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason = "This gateway has reached the maximum capacity of 10 for devices")
public class GatewayDeviceLimitReachedException  extends Exception
{
    private static final long serialVersionUID = 100L;
}