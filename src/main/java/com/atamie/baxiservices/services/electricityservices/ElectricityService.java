package com.atamie.baxiservices.services.electricityservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.http.ResponseEntity;

public interface ElectricityService {
    ResponseEntity<ServiceProviders> getALlElectricityServiceProviders();
}
