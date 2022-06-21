package com.atamie.baxiservices.services.databundleservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.services.airtimeservices.AirtimeBundleService;
import com.atamie.baxiservices.services.airtimeservices.AirtimeProvidersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class DataBundleProvidersService extends AirtimeProvidersService{

    private String BundlePath;

    public DataBundleProvidersService (final RestTemplateBuilder builder,
                             @Value("${BAXI.BASE.URL}") String URL,
                             @Value("${AIRTIME.PROVIDERS}") String AIR_PATH,
                             @Value("${API.KEY}") String API_KEY,
                                       @Value("${DATABUNDLE.PROVIDERS}") String Bundle){
        super(builder,URL,AIR_PATH,API_KEY);
        this.BundlePath = Bundle;

    }
    @Override
    public ResponseEntity<ServiceProviders> getAllAirtimeServiceProviders(){
        ResponseEntity<ServiceProviders> responseEntity;

        try{
            URI uri = new URI(BASE_URL+ BundlePath);
            responseEntity = restTemplate.exchange(uri, HttpMethod.GET,httpEntity,ServiceProviders.class);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            ServiceProviders serviceProviders = new ServiceProviders();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }

    }
}
