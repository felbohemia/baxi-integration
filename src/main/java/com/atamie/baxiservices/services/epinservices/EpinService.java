package com.atamie.baxiservices.services.epinservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.http.ResponseEntity;

public interface EpinService {

    ResponseEntity<ServiceProviders> getAllEpinServiceProviders();
}
