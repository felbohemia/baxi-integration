package com.atamie.baxiservices.services.epinservices;

import com.atamie.baxiservices.domain.cabletv.CableTVBundlesResponse;
import com.atamie.baxiservices.domain.epin.EpinBundlesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class EpinProvidersBundlesService extends EpinProvidersService{
    private String EPIN_PATH;
    public EpinProvidersBundlesService(RestTemplateBuilder builder,
                                       @Value("${BAXI.BASE.URL}") String URL,
                                       @Value("${SERVICES.EPIN.PROVIDERS}") String AIR_PATH,
                                       @Value("${API.KEY}") String API_KEY,
                                       @Value("${SERVICES.EPIN.PROVIDER.BUNDLE}") String path) {
        super(builder, URL, AIR_PATH, API_KEY);
        this.EPIN_PATH = path;
    }

    public ResponseEntity<EpinBundlesResponse> getEpinProviderBundles(final String service_type ){
        ResponseEntity<EpinBundlesResponse> responseEntity;

        try{
            String path = "?service_type="+service_type;
            URI uri = new URI(BASE_URL+ EPIN_PATH+path);
            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,EpinBundlesResponse.class);
            //log.info("path = {}", responseEntity);
            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            EpinBundlesResponse serviceProviders = new EpinBundlesResponse();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }
}
