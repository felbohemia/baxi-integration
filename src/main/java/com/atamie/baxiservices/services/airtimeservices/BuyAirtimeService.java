package com.atamie.baxiservices.services.airtimeservices;

import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j

public class BuyAirtimeService extends AirtimeProvidersService {

    private String PLAN;
    private int ID;
    private String AirtimeBuyPath;
    //private HttpEntity httpEntity;
    public BuyAirtimeService(final RestTemplateBuilder builder,
                             @Value("${BAXI.BASE.URL}") String URL,
                             @Value("${AIRTIME.PROVIDERS}") String AIR_PATH,
                             @Value("${API.KEY}") String API_KEY,
                             @Value("${BAXI.AGENT.ID}") int id,
                             @Value("${SERVICE.PLAN}") String PLAN,
                             @Value("${AIRTIME.BUY}") String BuyAirtimePath){

        super(builder,URL,AIR_PATH,API_KEY);
        this.PLAN = PLAN;
        this.ID = id;
        this.AirtimeBuyPath = BuyAirtimePath;

    }


    public ResponseEntity<CustomServiceProviders> buyAirtime(final String phone, final int amount, final String service_type) {
        ResponseEntity<CustomServiceProviders> responseEntity;

        try{
            String path = "?phone="+phone+"&amount="+amount
                    +"&service_type="+service_type+"&plan="+PLAN+"&agentId="+ID+"&agentReference="+ AgentReferenceGenerator.generateReference(12);
            URI uri = new URI(BASE_URL+ AirtimeBuyPath+path);

            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,CustomServiceProviders.class);
            //log.info("path = {}", responseEntity);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            CustomServiceProviders serviceProviders = new CustomServiceProviders();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }

}
