package com.atamie.baxiservices.services.electricityservices;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.electricity.ElectricityBuyRequestBody;
import com.atamie.baxiservices.domain.electricity.ElectricityBuyRequestBodyDTO;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URI;

@Service

public class ElectricityBillPayService extends ElectricityBundleService{
    private String ElectBuyPath;
    private int ID;

    public ElectricityBillPayService(RestTemplateBuilder builder,
                                     @Value("${BAXI.BASE.URL}") String URL,
                                     @Value("${SERVICES.BILLERS.ELECTRICITY}") String AIR_PATH,
                                     @Value("${API.KEY}") String API_KEY,
                                     @Value("${SERVICES.BILLERS.ELECTRICITY.BUNDLE}") String path,
                                     @Value("${SERVICES.BILLERS.ELECTRICITY.REQUEST}") String ElecBuyPath,
                                     @Value("${BAXI.AGENT.ID}") int id) {
        super(builder, URL, AIR_PATH, API_KEY, path);
        this.ElectBuyPath=ElecBuyPath;
        this.ID = id;
    }

    public ResponseEntity<?> payElectricityBill(ElectricityBuyRequestBodyDTO dto){
        ResponseEntity<String> responseEntity;
        ElectricityBuyRequestBody requestBody = new ElectricityBuyRequestBody();
        requestBody.setService_type(dto.getService_type());
        requestBody.setAccount_number(dto.getAccount_number());
        requestBody.setAmount(dto.getAmount());
        requestBody.setPhone(dto.getPhone());
        requestBody.setAgentId(ID);
        requestBody.setAgentReference(AgentReferenceGenerator.generateReference(12));
        httpEntity = new HttpEntity(requestBody, headers);

        try{
            URI uri = new URI(BASE_URL+ ElectBuyPath);

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
