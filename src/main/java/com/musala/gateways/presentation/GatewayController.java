package com.musala.gateways.presentation;

import com.musala.gateways.core.gateway.Gateway;
import com.musala.gateways.core.gateway.GatewayDevice;
import com.musala.gateways.exception.DeviceNotFoundException;
import com.musala.gateways.exception.GatewayDeviceLimitReachedException;
import com.musala.gateways.exception.GatewayExistsException;
import com.musala.gateways.exception.GatewayNotFoundException;
import com.musala.gateways.exception.InvalidIPv4AddressException;
import com.musala.gateways.infrastructure.device.DeviceService;
import com.musala.gateways.infrastructure.gateway.GatewayDeviceService;
import com.musala.gateways.infrastructure.gateway.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final GatewayService gatewayService;
    private final GatewayDeviceService gatewayDeviceService;

    @Autowired
    public GatewayController(GatewayService gatewayService,
                             GatewayDeviceService gatewayDeviceService){
        this.gatewayService = gatewayService;
        this.gatewayDeviceService = gatewayDeviceService;
    }

    @PostMapping("/create")
    public ResponseEntity createGateway(@RequestBody Gateway gateway) throws GatewayExistsException, InvalidIPv4AddressException {
        gatewayService.addGateway(gateway);
        return ResponseEntity.ok("Successfully created Gateway: " + gateway.toString());
    }

    @PostMapping("/update")
    public ResponseEntity updateGateway(@RequestBody Gateway gateway) throws GatewayNotFoundException {
        gatewayService.updateGateway(gateway);
        return ResponseEntity.ok("Successfully updated Gateway: " + gateway.toString());
    }

    @PostMapping("/delete/{serialNumber}")
    public ResponseEntity deleteGateway(@PathVariable String serialNumber) throws GatewayNotFoundException {
        gatewayService.deleteGateway(serialNumber);
        return ResponseEntity.ok("Successfully deleted Gateway with serial number: " + serialNumber);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity getGateway(@PathVariable String serialNumber) throws GatewayNotFoundException {
        return ResponseEntity.ok("Successfully fetched Gateway: " + gatewayDeviceService.getGatewayInfo(serialNumber));
    }

    @GetMapping("/get-all-info")
    public ResponseEntity getGateway() throws GatewayNotFoundException {
        return ResponseEntity.ok("Successfully fetched Gateways: " + gatewayDeviceService.getAllGatewayInfo());
    }

    @PostMapping("/add-device")
    public ResponseEntity addDevice(@RequestBody GatewayDevice gatewayDevice) throws DeviceNotFoundException, GatewayNotFoundException, GatewayDeviceLimitReachedException {
        gatewayDeviceService.addDevice(gatewayDevice);
        return ResponseEntity.ok("Successfully added Device: " + gatewayDevice.getUid() + " from Gateway: " + gatewayDevice.getSerialNumber());
    }

    @PostMapping("/remove-device")
    public ResponseEntity removeDevice(@RequestBody GatewayDevice gatewayDevice) throws DeviceNotFoundException, GatewayNotFoundException {
        gatewayDeviceService.deleteDevice(gatewayDevice);
        return ResponseEntity.ok("Successfully remove Device: " + gatewayDevice.getUid() + " from Gateway: " + gatewayDevice.getSerialNumber());
    }
}
