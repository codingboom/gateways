package com.musala.gateways.core.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.gateways.core.device.Device;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@ToString
@Getter
public class GatewayDevice {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("uid")
    private String uid;
}
