package com.atamie.baxiservices.services.tvservices;

import com.atamie.baxiservices.domain.cabletv.BuyCableTVBundleResponse;
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
public class BuyCableTVBundlesService extends CableTVBundlesService{
    private int ID;
    private String CableTVBuyPath;

    public BuyCableTVBundlesService(RestTemplateBuilder builder,
                                    @Value("${BAXI.BASE.URL}") String URL,
                                    @Value("${SERVICES.CABLETV.PROVIDERS}")String AIR_PATH,
                                    @Value("${API.KEY}")String API_KEY,
                                    @Value("${SERVICES.CABLETV.BUNDLES}") String path,
                                    @Value("${SERVICES.BUYCABLETV.SUBSCRIPTION}") String cableTVBuyPath,
                                    @Value("${BAXI.AGENT.ID}") int id) {
        super(builder, URL, AIR_PATH, API_KEY, path);
        this.ID = id;
        this.CableTVBuyPath = cableTVBuyPath;

    }

    public ResponseEntity<?> buyCableTVBundle(final String smartcard_number, final int total_amount,
                                              final String product_code, final String product_monthsPaidFor,
                                              final String service_type) {
        ResponseEntity<BuyCableTVBundleResponse> responseEntity;

        try{
            String path = "?smartcard_number="
                    +smartcard_number+"&total_amount="+total_amount
                    +"&product_code="+product_code+"&product_monthsPaidFor="+product_monthsPaidFor
                    +"&service_type="+service_type+"&agentId="+ID+"&agentReference="
                    + AgentReferenceGenerator.generateReference(12);
            URI uri = new URI(BASE_URL+ CableTVBuyPath+path);

            responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity,BuyCableTVBundleResponse.class);
            //log.info("path = {}", responseEntity1);

            return ResponseEntity.ok(responseEntity.getBody());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            BuyCableTVBundleResponse serviceProviders = new BuyCableTVBundleResponse();
            serviceProviders.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(serviceProviders);
        }
    }
}
