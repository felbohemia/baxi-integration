package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.services.airtimeservices.BuyAirtimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AirtimeController.class)

public class AirtimeControllerTest {

    @MockBean
    private BuyAirtimeService buyAirtimeService;
    //

    @Autowired
    private MockMvc mvc;//needed to simulate http requests

    @Test
    public void getAirtimeProvidersTest() throws Exception{
        ServiceProviders serviceProviders = new ServiceProviders();
        serviceProviders.setMessage("success");
        serviceProviders.setCode(200);
        serviceProviders.setStatus("success");
        serviceProviders.setData(null);

        ResponseEntity<ServiceProviders> responseEntity = ResponseEntity.ok(serviceProviders);

        given(buyAirtimeService.getAllAirtimeServiceProviders()).willReturn(responseEntity);
        MockHttpServletResponse response = mvc.perform( get("/airtime/airtime_providers")).andReturn().getResponse();
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(buyAirtimeService).getAllAirtimeServiceProviders();

    }

    @Test
    public void buyAirtime() throws Exception{
        //final String phone, final int amount, final String service_type
        String paras = "?phone=08132842116&amount=200&service_type=mtn";
        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("success");
        serviceProviders.setCode("200");
        serviceProviders.setStatus("success");
        serviceProviders.setData(null);

        ResponseEntity<CustomServiceProviders> responseEntity = ResponseEntity.ok(serviceProviders);

        given(buyAirtimeService.buyAirtime("08132842116",200,"mtn")).willReturn(responseEntity);
        MockHttpServletResponse response = mvc.perform( post("/airtime/buy_airtime"+paras)).andReturn().getResponse();
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(buyAirtimeService).buyAirtime("08132842116",200,"mtn");
    }

}
