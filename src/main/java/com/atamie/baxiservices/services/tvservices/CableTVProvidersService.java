package com.atamie.baxiservices.services.tvservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
@Service

public class CableTVProvidersService implements CableTVService{

    protected RestTemplate restTemplate;
    protected String BASE_URL;
    protected String CABLE_PATH;
    protected   String API_KEY;
    protected HttpEntity httpEntity;

    public CableTVProvidersService(final RestTemplateBuilder builder,
                                   @Value("${BAXI.BASE.URL}") String URL,
                                   @Value("${SERVICES.CABLETV.PROVIDERS}") String AIR_PATH,
                                   @Value("${API.KEY}") String API_KEY){

        this.restTemplate = builder.build();
        this.CABLE_PATH = AIR_PATH;
        this.API_KEY = API_KEY;
        this.BASE_URL = URL;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",API_KEY);
        headers.set("Accept","application/json");
        httpEntity = new HttpEntity(headers);
    }

    @Override
    public ResponseEntity<ServiceProviders> getAllCableTvServiceProviders() {
        ResponseEntity<ServiceProviders> responseEntity;

        try{
            URI uri = new URI(BASE_URL+ CABLE_PATH);
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
