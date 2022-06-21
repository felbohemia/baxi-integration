package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.databundle.BundleResponses;
import com.atamie.baxiservices.domain.databundle.BuyBundleResponse;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.databundleservices.BuyDataBundleService;
import com.atamie.baxiservices.services.databundleservices.DataBundlesService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bundle")
public class DataBundleController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BuyDataBundleService airtimeBundleService;


    @GetMapping("/bundle_providers")
    public ResponseEntity<ServiceProviders> getDataBundleProviders(){
        return airtimeBundleService.getAllAirtimeServiceProviders();
    }

    @PostMapping("/providers_bundles")
    public ResponseEntity<BundleResponses> getAvailableBundles(@RequestParam("service_type")String service_type){
        return airtimeBundleService.getDataBundleProvidersBundle(service_type);
    }
    @PostMapping("/buy_databundle")
    public ResponseEntity<?> buyDataBundle( @RequestParam("phone") String phone,
                                            @RequestParam("amount") int amount,
                                            @RequestParam("service_type") String service_type,
                                            @RequestParam("datacode")String datacode,
                                             HttpServletRequest request)
    {
        final String requestTokenHeader = request.getHeader("Authorization");
        String account = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            //log.info("account {}", jwtToken);
            try {
                account = jwtTokenUtil.getUsernameFromToken(jwtToken);
                //log.info("account {}", account);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        if(accountService.debitWalletDuringPurchase(account,amount))
            return airtimeBundleService.buyDataBundle(phone,amount,service_type,datacode);

        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry, you do not have sufficient balance to buy the data bundle");
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
