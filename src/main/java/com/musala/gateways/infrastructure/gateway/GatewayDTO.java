package com.musala.gateways.infrastructure.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "GATEWAY")
public class GatewayDTO {
    @Id
    private String serialNumber;

    private String name;

    private String ipv4Address;
}
