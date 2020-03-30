package com.musala.gateways.infrastructure.device;

import com.musala.gateways.core.device.Device;
import com.musala.gateways.core.device.Device;
import com.musala.gateways.exception.DeviceExistsException;
import com.musala.gateways.exception.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> getDevices() {
        return deviceRepository.findAll();
    }

    public void createDevice(Device device) throws DeviceExistsException {
        if(deviceRepository.findById(device.getUid()).isPresent()) throw new DeviceExistsException();
        deviceRepository.save(toDeviceDTO(device));
    }

    public void updateDevice(Device device) throws DeviceNotFoundException {
        deviceRepository.findById(device.getUid()).orElseThrow(() -> new DeviceNotFoundException());
        deviceRepository.save(toDeviceDTO(device));
    }

    public void deleteDevice(String uid) throws DeviceNotFoundException {
        deviceRepository.delete(deviceRepository.findById(uid).orElseThrow(() -> new DeviceNotFoundException()));
    }

    private DeviceDTO toDeviceDTO(Device device){
        return DeviceDTO.builder()
                .uid(device.getUid())
                .vendor(device.getVendor())
                .dateCreated(device.getDateCreated())
                .status(device.getStatus().name())
                .build();
    }
}
