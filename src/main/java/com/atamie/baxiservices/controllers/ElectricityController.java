package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.electricity.ElectricityBundle;
import com.atamie.baxiservices.domain.electricity.ElectricityBuyRequestBodyDTO;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.electricityservices.ElectricityBillPayService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/electric")
public class ElectricityController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ElectricityBillPayService billPayService;

    public ElectricityController(ElectricityBillPayService billPayService){
        this.billPayService = billPayService;
    }

    @GetMapping("/billers")
    public ResponseEntity<ServiceProviders> getElectricBillers(){
        return billPayService.getALlElectricityServiceProviders();
    }

    @PostMapping("/verifyuseraccount")
    public ResponseEntity<?> verifyUserAccount(@RequestBody  ElectricityBundle bundle){
        return billPayService.verifyUserAccount(bundle);
    }

    @PostMapping("/payelectricbill")
    public ResponseEntity<?> payElectricBill(@RequestBody ElectricityBuyRequestBodyDTO electricDTO, HttpServletRequest request){

        final String requestTokenHeader = request.getHeader("Authorization");
        String account = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                account = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (Exception e) {
                System.out.println("JWT Token has expired");
            }
        }
        if(accountService.debitWalletDuringPurchase(account,Double.parseDouble(electricDTO.getAmount())))
            return billPayService.payElectricityBill(electricDTO);

        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry, you do not have sufficient balance to pay for electric bill");
        return ResponseEntity.badRequest().body(serviceProviders);
    }
    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String  account = jwtTokenUtil.getUsernameFromToken(jwtToken);
        return accountService.getAccountByAccountNumber(account);
    }
}
