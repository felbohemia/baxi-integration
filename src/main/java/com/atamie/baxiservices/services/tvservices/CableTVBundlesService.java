package com.atamie.baxiservices.services.tvservices;

import com.atamie.baxiservices.domain.cabletv.CableTVBundlesResponse;
import com.atamie.baxiservices.domain.databundle.BundleResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CableTVBundlesService  extends CableTVProvidersService{

    private String CableTVBundlePath;
    public CableTVBundlesService(RestTemplateBuilder builder,
                                 @Value("${BAXI.BASE.URL}") String URL,
                                 @Value("${SERVICES.CABLETV.PROVIDERS}")String AIR_PATH,
                                 @Value("${API.KEY}")String API_KEY,
                                 @Value("${SERVICES.CABLETV.BUNDLES}") String path) {
        super(builder, URL, AIR_PATH, API_KEY);
        this.CableTVBundlePath = path;
    }

    public ResponseEntity<CableTVBundlesResponse> getCableTVProvidersBundles(final String service_type ){
        ResponseEntity<CableTVBundlesResponse> responseEntity;

        try{
            String path = "?service_type="+service_type;
            URI uri = new URI(BASE_URL+ CableTVBundlePath+path);
            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,CableTVBundlesResponse.class);
            //log.info("path = {}", responseEntity);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            CableTVBundlesResponse serviceProviders = new CableTVBundlesResponse();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }
}
