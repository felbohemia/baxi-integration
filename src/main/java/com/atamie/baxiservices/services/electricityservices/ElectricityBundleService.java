package com.atamie.baxiservices.services.electricityservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.electricity.ElectricityBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service
public class ElectricityBundleService extends ElectricityBillersService{

    private String ELECT_PATH;
    public ElectricityBundleService(final RestTemplateBuilder builder,
                                    @Value("${BAXI.BASE.URL}") String URL,
                                    @Value("${SERVICES.BILLERS.ELECTRICITY}") String AIR_PATH,
                                    @Value("${API.KEY}") String API_KEY,
                                    @Value("${SERVICES.BILLERS.ELECTRICITY.BUNDLE}") String path) {
        super(builder, URL, AIR_PATH, API_KEY);
        this.ELECT_PATH = path;

    }

    public ResponseEntity<?> verifyUserAccount(final ElectricityBundle bundle){
        ResponseEntity<String> responseEntity;
        httpEntity = new HttpEntity(bundle,headers);
        try{
            URI uri = new URI(BASE_URL+ ELECT_PATH);

            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity, String.class);
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
