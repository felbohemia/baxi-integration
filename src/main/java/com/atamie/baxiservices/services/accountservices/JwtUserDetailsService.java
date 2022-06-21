package com.atamie.baxiservices.services.accountservices;

import com.atamie.baxiservices.domain.account.Account;
import com.atamie.baxiservices.domain.account.AcctValDTO;
import com.atamie.baxiservices.repositories.accountrepositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public static int noOfQuickServiceThreads = 20;
    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

    private String password;

    /*@Autowired
    private PasswordEncoder bcryptEncoder;

     */


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> user = accountRepository.findByEmailAndPassword(email,password);

        Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

        if (user.isEmpty()) {
            return new User("Invalid Username","Invalid password", new ArrayList<>());

        }
        Account user1 = user.get();
        //log.info(" Hello:  {}", user1 );
        return User.withUsername(user1.getAccount()).password(password).roles("USER").build();
    }
    public Optional<Account> validateUser(AcctValDTO acctValDTO){
        password = acctValDTO.getPassword();
        Optional<Account> account = accountRepository.findByEmailAndPassword(acctValDTO.getEmail(),acctValDTO.getPassword());
        return account;
    }

    public Optional<Account> getAccountByAccountNumber(String account){
        return  accountRepository.findByAccount(account);
    }

}

