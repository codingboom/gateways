package com.musala.gateways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "This device is not found in the system")
public class DeviceNotFoundException extends Exception
{
    private static final long serialVersionUID = 100L;
}
