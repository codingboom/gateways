package com.musala.gateways.infrastructure.gateway;

import com.musala.gateways.infrastructure.device.DeviceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "GATEWAY_DEVICE")
@EqualsAndHashCode
public class GatewayDeviceDTO implements Serializable {

    @EmbeddedId
    private GatewayDeviceID gatewayDeviceID = new GatewayDeviceID();

    @ManyToOne
    @org.hibernate.annotations.Cascade(CascadeType.DELETE_ORPHAN)
    @MapsId("serialNumber")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "gateway_serial_number", updatable = false, nullable = false)
    private GatewayDTO gateway;

    @ManyToOne
    @org.hibernate.annotations.Cascade(CascadeType.DELETE_ORPHAN)
    @MapsId("uid")
    @JoinColumn(name = "device_uid", updatable = false, nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DeviceDTO device;
}


