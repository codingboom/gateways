package com.musala.gateways.infrastructure.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musala.gateways.core.device.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "DEVICE")
public class DeviceDTO {
    @Id
    @Column(name = "UID")
    private String uid;

    @Column(name = "VENDOR")
    private String vendor;

    @Column(name = "DATE_CREATE")
    private LocalDate dateCreated;

    @Column(name = "STATUS")
    private String status;
}
