package com.musala.gateways.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason = "This device has an entry in the system")
public class DeviceExistsException extends Exception
{
    private static final long serialVersionUID = 100L;
}
