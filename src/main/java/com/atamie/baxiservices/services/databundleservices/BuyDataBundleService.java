package com.atamie.baxiservices.services.databundleservices;
import com.atamie.baxiservices.domain.databundle.BuyBundleResponse;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j

public class BuyDataBundleService extends DataBundlesService {
    //private String PLAN;
    private int ID;
    private String AirtimeBuyPath;
    //private HttpEntity httpEntity;
    public BuyDataBundleService(final RestTemplateBuilder builder,
                             @Value("${BAXI.BASE.URL}") String URL,
                             @Value("${AIRTIME.PROVIDERS}") String AIR_PATH,
                             @Value("${API.KEY}") String API_KEY,
                             @Value("${BAXI.AGENT.ID}") int id,
                             @Value("${DATABUNDLE.BUY}") String BuyAirtimePath) {

        super(builder, URL, AIR_PATH, API_KEY, BuyAirtimePath);
        this.ID = id;
        this.AirtimeBuyPath = BuyAirtimePath;
    }
    public ResponseEntity<?> buyDataBundle(final String phone, final int amount, final String service_type, final String datacode) {
        ResponseEntity<BuyBundleResponse> responseEntity;

        try{
            String path = "?phone="
                    +phone+"&amount="+amount
                    +"&service_type="+service_type+"&datacode="
                    +datacode+"&agentId="+ID+"&agentReference="
                    + AgentReferenceGenerator.generateReference(12);
            URI uri = new URI(BASE_URL+ AirtimeBuyPath+path);

            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,BuyBundleResponse.class);
            //log.info("path = {}", responseEntity);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            BuyBundleResponse serviceProviders = new BuyBundleResponse();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }

}
