package com.musala.gateways.infrastructure.gateway;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GatewayDeviceID implements Serializable {
    private String serialNumber;
    private String uid;
}
