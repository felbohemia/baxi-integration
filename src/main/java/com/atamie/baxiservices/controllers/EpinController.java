package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.epin.EpinBundlesResponse;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.epinservices.BuyEpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/epin")
public class EpinController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BuyEpinService buyEpinService;
    public EpinController(BuyEpinService buyEpinService){
        this.buyEpinService = buyEpinService;
    }

    @GetMapping("/epin_providers")
    public ResponseEntity<ServiceProviders> getEPinProviders(){
        return buyEpinService.getAllEpinServiceProviders();
    }

    @PostMapping("/epin_bundles")
    public ResponseEntity<EpinBundlesResponse> getEpinBundles(@RequestParam("service_type") String service_type){
        return buyEpinService.getEpinProviderBundles(service_type);
    }

    @PostMapping("/buy_epin")
    public ResponseEntity<?>  buyEpin(@RequestParam("service_type") String service_type,
                                      @RequestParam("numberOfPins") int numberOfPins,
                                      @RequestParam("pinValue") int pinValue,
                                      @RequestParam("amount") int amount,
                                      HttpServletRequest request){

        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String  account = jwtTokenUtil.getUsernameFromToken(jwtToken);
        if(accountService.debitWalletDuringPurchase(account,amount))
            return buyEpinService.buyEpin(service_type,numberOfPins,pinValue,amount);

        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry, you do not have sufficient balance to buy the "+ service_type+" pin");
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
