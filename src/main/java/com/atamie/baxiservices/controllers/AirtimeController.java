package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.domain.airtime.ServiceProviders;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.airtimeservices.BuyAirtimeService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequiredArgsConstructor
@Slf4j
@RequestMapping("/airtime")
public class AirtimeController {

    @Autowired
    private BuyAirtimeService buyAirtimeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @GetMapping("/airtime_providers")
    public ResponseEntity<ServiceProviders> getAirtimeProviders(){
        return buyAirtimeService.getAllAirtimeServiceProviders();
    }

    @PostMapping("/buy_airtime")
    public ResponseEntity<CustomServiceProviders> buyAirtime(
            @RequestParam("phone") String phone,
            @RequestParam("amount") int amount,
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

        if(accountService.debitWalletDuringPurchase(account,amount))
            return buyAirtimeService.buyAirtime(phone,amount,service_type);

        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry, you do not have sufficient Balance to buy the airtime");
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
