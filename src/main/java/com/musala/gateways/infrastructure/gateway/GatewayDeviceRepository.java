package com.musala.gateways.infrastructure.gateway;

import com.musala.gateways.infrastructure.device.DeviceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayDeviceRepository extends JpaRepository<GatewayDeviceDTO, String> {
    public GatewayDeviceDTO findFirstByDeviceAndGateway(DeviceDTO device, GatewayDTO gateway);
    public List<GatewayDeviceDTO> findAllByGateway(GatewayDTO gatewayDTO);
}
