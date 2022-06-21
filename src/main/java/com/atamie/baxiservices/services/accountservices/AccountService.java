package com.atamie.baxiservices.services.accountservices;

import com.atamie.baxiservices.domain.account.*;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<AccountCreationResponse> createAccount(AccountDTO accountDTO);
    CreditAccount depositIntoWallet(CreditAccountDTO creditAccountDTO);
    boolean debitWalletDuringPurchase(String acct, double amount);
    ResponseEntity<?> getAccountByAccountNumber(String accountNumber);
    //ResponseEntity<?> validateAccount(AcctValDTO acctValDTO);
}
