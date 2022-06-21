package com.atamie.baxiservices.services.airtimeservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.http.ResponseEntity;

public interface AirtimeBundleService {
     ResponseEntity<ServiceProviders> getAllAirtimeServiceProviders();
}
