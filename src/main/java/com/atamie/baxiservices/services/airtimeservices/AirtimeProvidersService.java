package com.atamie.baxiservices.services.airtimeservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AirtimeProvidersService implements AirtimeBundleService{
    protected RestTemplate restTemplate;
    protected String BASE_URL;
    protected String AIR_PATH;
    protected   String API_KEY;
    protected HttpEntity httpEntity;

    public AirtimeProvidersService(){

    }

    public AirtimeProvidersService(final RestTemplateBuilder builder,
                                   @Value("${BAXI.BASE.URL}") String URL,
                                   @Value("${AIRTIME.PROVIDERS}") String AIR_PATH,
                                   @Value("${API.KEY}") String API_KEY){

        this.restTemplate = builder.build();
        this.AIR_PATH = AIR_PATH;
        this.API_KEY = API_KEY;
        this.BASE_URL = URL;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",API_KEY);
        headers.set("Accept","application/json");
        httpEntity = new HttpEntity(headers);
    }
    // for unit testing
    public AirtimeProvidersService(RestTemplate template, String baseUrl, String airPath, String apiKey){
        this.restTemplate = template;
        this.AIR_PATH = airPath;
        this.API_KEY = apiKey;
        this.BASE_URL = baseUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",API_KEY);
        headers.set("Accept","application/json");
        httpEntity = new HttpEntity(headers);
    }
    public ResponseEntity<ServiceProviders> getAllAirtimeServiceProviders(){
        ResponseEntity<ServiceProviders> responseEntity;

        try{
            URI uri = new URI(BASE_URL+ AIR_PATH);
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
