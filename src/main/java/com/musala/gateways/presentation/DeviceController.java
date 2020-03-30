package com.musala.gateways.presentation;

import com.musala.gateways.core.device.Device;
import com.musala.gateways.core.gateway.Gateway;
import com.musala.gateways.exception.DeviceExistsException;
import com.musala.gateways.exception.DeviceNotFoundException;
import com.musala.gateways.exception.GatewayNotFoundException;
import com.musala.gateways.infrastructure.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @PostMapping("/create")
    public ResponseEntity asd(@RequestBody Device device) throws DeviceExistsException {
        deviceService.createDevice(device);
        return new ResponseEntity<Object>("Successfully created Device: " + device, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateDevice(@RequestBody Device device) throws DeviceNotFoundException {
        deviceService.updateDevice(device);
        return ResponseEntity.ok("Successfully updated Device: " + device.toString());
    }

    @PostMapping("/delete/{uid}")
    public ResponseEntity deleteDevice(@PathVariable String uid) throws DeviceNotFoundException {
        deviceService.deleteDevice(uid);
        return ResponseEntity.ok("Successfully deleted Device with UID: " + uid);
    }

}
