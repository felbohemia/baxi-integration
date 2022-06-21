package com.atamie.baxiservices.services.tvservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.http.ResponseEntity;

public interface CableTVService {

    ResponseEntity<ServiceProviders> getAllCableTvServiceProviders();
}
