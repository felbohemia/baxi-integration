package com.atamie.baxiservices.services;

import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.services.airtimeservices.AirtimeProvidersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class AirtimeProviderServiceTest {

    private AirtimeProvidersService airtimeProvidersService;
    private final String basdUrl = "https://payments.baxipay.com.ng/api/baxipay/";
    private final String airProviders = "services/airtime/providers";
    private final String apiKey = "Api-key 5adea9-044a85-708016-7ae662-646d59";

    @Spy
    private RestTemplate template;

    @BeforeEach
    public void setUp(){
        //log.info("API key {}", apiKey);
        airtimeProvidersService = new AirtimeProvidersService(template,basdUrl,airProviders,apiKey);
    }
    /*
      Testing of other services takes a similar shape.
     */
    @Test
    public void airtimeProvidersTest () throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",apiKey);
        headers.set("Accept","application/json");
        URI uri = new URI(basdUrl+ airProviders);
        HttpEntity httpEntity = new HttpEntity(headers);

        ServiceProviders serviceProviders = new ServiceProviders();
        serviceProviders.setMessage("success");
        serviceProviders.setCode(200);
        serviceProviders.setStatus("success");
        serviceProviders.setData(null);

        ResponseEntity<ServiceProviders> responseEntity = ResponseEntity.ok(serviceProviders);
        // configures the spy... a partial mock object
        given(template.exchange(uri, HttpMethod.GET,httpEntity, ServiceProviders.class)).willReturn(responseEntity);
        then(responseEntity.getBody()).isEqualTo(serviceProviders);
        verify(template).exchange(uri, HttpMethod.GET,httpEntity, ServiceProviders.class);

    }
}
