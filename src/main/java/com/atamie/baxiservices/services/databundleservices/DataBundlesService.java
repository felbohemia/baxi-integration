package com.atamie.baxiservices.services.databundleservices;

import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.databundle.BundleResponses;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import com.atamie.baxiservices.services.airtimeservices.AirtimeProvidersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class DataBundlesService extends AirtimeProvidersService {
    private String BundlePath;

    public DataBundlesService (final RestTemplateBuilder builder,
                                       @Value("${BAXI.BASE.URL}") String URL,
                                       @Value("${AIRTIME.PROVIDERS}") String AIR_PATH,
                                       @Value("${API.KEY}") String API_KEY,
                                       @Value("${SERVICES.PROVIDERS.BUNDLE}") String Bundle) {
        super(builder, URL, AIR_PATH, API_KEY);
        this.BundlePath = Bundle;
    }

    public ResponseEntity<BundleResponses> getDataBundleProvidersBundle(final String service_type ){
        ResponseEntity<BundleResponses> responseEntity;

        try{
            String path = "?service_type="+service_type;
            URI uri = new URI(BASE_URL+ BundlePath+path);
            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,BundleResponses.class);
            //log.info("path = {}", responseEntity);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            BundleResponses serviceProviders = new BundleResponses();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }
}
