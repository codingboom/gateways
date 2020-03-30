package com.musala.gateways.infrastructure.gateway;

import com.musala.gateways.core.gateway.Gateway;
import com.musala.gateways.exception.GatewayExistsException;
import com.musala.gateways.exception.GatewayNotFoundException;
import com.musala.gateways.exception.InvalidIPv4AddressException;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {

    private final GatewayRepository gatewayRepository;
    private final InetAddressValidator inetAddressValidator;

    @Autowired
    public GatewayService(GatewayRepository gatewayRepository){
        this.gatewayRepository = gatewayRepository;
        this.inetAddressValidator = new InetAddressValidator();
    }

    public List<GatewayDTO> getGateways() {
        return gatewayRepository.findAll();
    }

    public void addGateway(Gateway gateway) throws GatewayExistsException, InvalidIPv4AddressException {
        if(gatewayRepository.findById(gateway.getSerialNumber()).isPresent()) throw new GatewayExistsException();
        if(!inetAddressValidator.isValid(gateway.getIpv4Address())) throw new InvalidIPv4AddressException();
        gatewayRepository.save(toGatewayDTO(gateway));
    }

    public void updateGateway(Gateway gateway) throws GatewayNotFoundException {
        gatewayRepository.findById(gateway.getSerialNumber()).orElseThrow(() -> new GatewayNotFoundException());
        gatewayRepository.save(toGatewayDTO(gateway));
    }

    public void deleteGateway(String serialNumber) throws GatewayNotFoundException {
        gatewayRepository.delete(gatewayRepository.findById(serialNumber).orElseThrow(() -> new GatewayNotFoundException()));
    }

    private GatewayDTO toGatewayDTO(Gateway gateway){
        return GatewayDTO.builder()
                .serialNumber(gateway.getSerialNumber())
                .name(gateway.getName())
                .ipv4Address(gateway.getIpv4Address())
                .build();
    }
}
