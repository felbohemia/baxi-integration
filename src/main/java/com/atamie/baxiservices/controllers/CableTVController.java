package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.domain.cabletv.BuyCableTVBundleResponse;
import com.atamie.baxiservices.domain.cabletv.CableTVBundlesResponse;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.tvservices.BuyCableTVBundlesService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cabletv")
public class CableTVController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BuyCableTVBundlesService buyCableTVBundlesService;
    public CableTVController(BuyCableTVBundlesService buyCableTVBundlesService){
        this.buyCableTVBundlesService = buyCableTVBundlesService;
    }
    @GetMapping("/tv_providers")
    public ResponseEntity<ServiceProviders> getTvProviders(){
        return buyCableTVBundlesService.getAllCableTvServiceProviders();
    }

    @PostMapping("/tv_bundles")
    public ResponseEntity<CableTVBundlesResponse> getTVBundles(@RequestParam("service_type") String service_type){
        return buyCableTVBundlesService.getCableTVProvidersBundles(service_type);
    }
    @PostMapping("/tv_buy_bundle")
    public ResponseEntity<?> buyTVBundle(@RequestParam("smartcard_number") String smartcard_number,
                                         @RequestParam("total_amount") int total_amount,
                                         @RequestParam("product_code") String product_code,
                                         @RequestParam("product_monthsPaidFor") String product_monthsPaidFor,
                                         @RequestParam("service_type") String service_type,
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
        if(accountService.debitWalletDuringPurchase(account,total_amount))
            return buyCableTVBundlesService.buyCableTVBundle(smartcard_number,total_amount,product_code,product_monthsPaidFor,service_type);

        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry, you do not have sufficient balance to pay for your tv subscription");
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
