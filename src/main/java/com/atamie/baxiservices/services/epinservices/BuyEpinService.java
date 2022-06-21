package com.atamie.baxiservices.services.epinservices;

import com.atamie.baxiservices.domain.cabletv.BuyCableTVBundleResponse;
import com.atamie.baxiservices.domain.epin.EpinBuyResponse;
import com.atamie.baxiservices.domain.epin.PinBuyData;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
@Service
public class BuyEpinService extends EpinProvidersBundlesService{

    private int ID;
    private String EPINBuyPath;

    public BuyEpinService(RestTemplateBuilder builder,
                          @Value("${BAXI.BASE.URL}") String URL,
                          @Value("${SERVICES.EPIN.PROVIDERS}") String AIR_PATH,
                          @Value("${API.KEY}") String API_KEY,
                          @Value("${SERVICES.EPIN.PROVIDER.BUNDLE}") String path,
                          @Value("${SERVICES.EPIN.BUY}") String EPINPATH,
                          @Value("${BAXI.AGENT.ID}") int id) {

        super(builder, URL, AIR_PATH, API_KEY, path);
        this.EPINBuyPath = EPINPATH;
        this.ID = id;

    }

    public ResponseEntity<?> buyEpin(final String service_type,
                                              final int numberOfPins,
                                              final int pinValue,
                                              final int amount) {
        ResponseEntity<EpinBuyResponse> responseEntity;

        try{
            String path = "?service_type=" +service_type
                          +"&numberOfPins="+numberOfPins
                          +"&pinValue="+pinValue
                          +"&amount="+amount
                         +"&agentId="+ID+"&agentReference="
                    + AgentReferenceGenerator.generateReference(12);
            URI uri = new URI(BASE_URL+ EPINBuyPath+path);

            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,EpinBuyResponse.class);
            //log.info("path = {}", responseEntity1);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            EpinBuyResponse serviceProviders = new EpinBuyResponse();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }
}
