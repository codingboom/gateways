package com.musala.gateways.core.gateway;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.gateways.core.device.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.Set;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gateway {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("ipv4_address")
    private String ipv4Address;

    @Nullable
    @JsonProperty("device")
    private Set<Device> device;
}
