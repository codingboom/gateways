package com.musala.gateways.infrastructure.gateway;

import com.musala.gateways.core.device.Device;
import com.musala.gateways.core.gateway.Gateway;
import com.musala.gateways.core.gateway.GatewayDevice;
import com.musala.gateways.exception.DeviceNotFoundException;
import com.musala.gateways.exception.GatewayDeviceLimitReachedException;
import com.musala.gateways.exception.GatewayNotFoundException;
import com.musala.gateways.infrastructure.device.DeviceDTO;
import com.musala.gateways.infrastructure.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GatewayDeviceService {
    private final GatewayDeviceRepository gatewayDeviceRepository;
    private final GatewayRepository gatewayRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public GatewayDeviceService(GatewayDeviceRepository gatewayDeviceRepository,
                                GatewayRepository gatewayRepository,
                                DeviceRepository deviceRepository){
        this.gatewayDeviceRepository = gatewayDeviceRepository;
        this.gatewayRepository = gatewayRepository;
        this.deviceRepository = deviceRepository;
    }

    public Gateway getGatewayInfo(String serialNumber) throws GatewayNotFoundException {
        List<GatewayDeviceDTO> gatewayDeviceDTOList = gatewayDeviceRepository.findAllByGateway(gatewayRepository.findById(serialNumber).orElseThrow(() -> new GatewayNotFoundException()));

        Set<Device> devices = new HashSet<>();
        for(GatewayDeviceDTO gatewayDeviceDTO : gatewayDeviceDTOList){
            devices.add(toDevice(gatewayDeviceDTO.getDevice()));
        }

        Gateway gateway = toGateway(gatewayRepository.findById(serialNumber).orElseThrow(() -> new GatewayNotFoundException()));
        gateway.setDevice(devices);

        return gateway;
    }

    public Set<Gateway> getAllGatewayInfo() throws GatewayNotFoundException {
        List<GatewayDTO> gatewayDTOS = gatewayRepository.findAll();
        Set<Gateway> gateways = new HashSet<>();

        for(GatewayDTO gatewayDTO : gatewayDTOS){
            gateways.add(getGatewayInfo(gatewayDTO.getSerialNumber()));
        }

        return gateways;
    }

    public void addDevice(GatewayDevice gatewayDevice) throws DeviceNotFoundException, GatewayNotFoundException, GatewayDeviceLimitReachedException {
        if(gatewayDeviceRepository.findAllByGateway(gatewayRepository.findById(gatewayDevice.getSerialNumber()).orElseThrow(() -> new GatewayNotFoundException())).size() == 10)
            throw new GatewayDeviceLimitReachedException();

        gatewayDeviceRepository.save(
                GatewayDeviceDTO.builder()
                        .gatewayDeviceID(new GatewayDeviceID(gatewayDevice.getSerialNumber(), gatewayDevice.getUid()))
                        .gateway(gatewayRepository.findById(gatewayDevice.getSerialNumber()).orElseThrow(() -> new GatewayNotFoundException()))
                        .device(deviceRepository.findById(gatewayDevice.getUid()).orElseThrow(() -> new DeviceNotFoundException()))
                        .build()
        );
    }

    public void deleteDevice(GatewayDevice gatewayDevice) throws DeviceNotFoundException, GatewayNotFoundException {
        GatewayDeviceDTO gatewayDeviceDTO = gatewayDeviceRepository.findFirstByDeviceAndGateway(
                deviceRepository.findById(gatewayDevice.getUid()).orElseThrow(() -> new DeviceNotFoundException()),
                gatewayRepository.findById(gatewayDevice.getSerialNumber()).orElseThrow(() -> new GatewayNotFoundException()));

        gatewayDeviceRepository.delete(gatewayDeviceDTO);
    }

    private Device toDevice(DeviceDTO deviceDTO){
        return Device.builder()
                .uid(deviceDTO.getUid())
                .vendor(deviceDTO.getVendor())
                .dateCreated(deviceDTO.getDateCreated())
                .status(Device.Status.valueOf(deviceDTO.getStatus()))
                .build();
    }

    private Gateway toGateway(GatewayDTO gatewayDTO){
        return Gateway.builder()
                .serialNumber(gatewayDTO.getSerialNumber())
                .name(gatewayDTO.getName())
                .ipv4Address(gatewayDTO.getIpv4Address())
                .build();
    }
}
