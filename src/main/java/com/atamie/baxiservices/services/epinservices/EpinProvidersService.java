package com.atamie.baxiservices.services.epinservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class EpinProvidersService implements EpinService{
    protected RestTemplate restTemplate;
    protected String BASE_URL;
    protected String EPIN_PATH;
    protected   String API_KEY;
    protected HttpEntity httpEntity;


    public EpinProvidersService(final RestTemplateBuilder builder,
                                   @Value("${BAXI.BASE.URL}") String URL,
                                   @Value("${SERVICES.EPIN.PROVIDERS}") String AIR_PATH,
                                   @Value("${API.KEY}") String API_KEY) {

        this.restTemplate = builder.build();
        this.EPIN_PATH = AIR_PATH;
        this.API_KEY = API_KEY;
        this.BASE_URL = URL;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", API_KEY);
        headers.set("Accept", "application/json");
        httpEntity = new HttpEntity(headers);
    }
    @Override
    public ResponseEntity<ServiceProviders> getAllEpinServiceProviders() {
        ResponseEntity<ServiceProviders> responseEntity;

        try{
            URI uri = new URI(BASE_URL+ EPIN_PATH);
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
