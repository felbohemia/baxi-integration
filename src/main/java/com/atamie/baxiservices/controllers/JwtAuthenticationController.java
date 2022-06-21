package com.atamie.baxiservices.controllers;

import com.atamie.baxiservices.configuration.JwtTokenUtil;
import com.atamie.baxiservices.domain.account.*;
import com.atamie.baxiservices.services.accountservices.AccountService;
import com.atamie.baxiservices.services.accountservices.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AcctValDTO authenticationRequest) throws Exception {

        userDetailsService.validateUser(authenticationRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if(userDetails.getUsername().equals("Invalid Username")){
            return ResponseEntity.status(401).body("Invalid Credentials");
        }

        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse response = new JwtResponse(token);
        //log.info("The token contains: {}", token);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<AccountCreationResponse> saveUser(@RequestBody AccountDTO user) throws Exception {
       return accountService.createAccount(user);
    }

    @PostMapping("/credit_account")
    public CreditAccount creditAccount(@RequestBody CreditAccountDTO creditAccountDTO){
        return accountService.depositIntoWallet(creditAccountDTO);
    }

}
