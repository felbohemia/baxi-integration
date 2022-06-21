package com.atamie.baxiservices.services.accountservices;

import com.atamie.baxiservices.domain.account.*;
import com.atamie.baxiservices.domain.airtime.CustomServiceProviders;
import com.atamie.baxiservices.repositories.accountrepositories.AccountRepository;
import com.atamie.baxiservices.repositories.accountrepositories.TransactionRepository;
import com.atamie.baxiservices.services.AgentReferenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<AccountCreationResponse> createAccount(AccountDTO accountDTO) {
       Account account = new Account();
        //ResponseEntity<AccountCreationResponse> responseEntity;
        AccountCreationResponse response = new AccountCreationResponse();
       Optional<Account> account1 = accountRepository.findByEmailAndPassword(accountDTO.getEmail(),accountDTO.getPassword());
       if(account1.isPresent()){
           //AccountCreationResponse response = new AccountCreationResponse();
           response.setEmail(accountDTO.getEmail());
           response.setPhone(accountDTO.getPhone());
           response.setMessage("You already have an account with us!");
           return ResponseEntity.badRequest().body(response);
       }
       /*
       Need to check if account number doesn't exist to prevent the possibility of account collision
       though, the database field for account is already declared unique
       The logic right here should check before making attempt to store anything on the database.
        */
       account.setAccount(AgentReferenceGenerator.generateAccountNumber());
       account.setBalance(0.0);
       account.setEmail(accountDTO.getEmail());
       account.setName(accountDTO.getName());
       account.setPassword(accountDTO.getPassword());
       account.setPhone(accountDTO.getPhone());
       account.setId(null);
       account.setPeriod(LocalDateTime.now());
       /*
       saves the new account to the database
        */
       accountRepository.save(account);

       response.setAccountNumber(account.getAccount());
       response.setEmail(account.getEmail());
       response.setMessage("Account creation successful");
       return ResponseEntity.ok(response);

    }

    @Override
    public CreditAccount depositIntoWallet(CreditAccountDTO creditAccountDTO) {
        Optional<Account> account = accountRepository.findByAccount(creditAccountDTO.getAccount());
        CreditAccount creditAccount = new CreditAccount();
        Transaction transaction = new Transaction();

        if(account.isPresent()){
            Account account1 = account.get();
            double balance = account1.getBalance();
            double newBalance = balance + creditAccountDTO.getAmount();

            transaction.setAccount(account1.getAccount());
            transaction.setId(null);
            transaction.setPeriod(LocalDateTime.now());
            transaction.setPhone(account1.getPhone());
            transaction.setAmount(creditAccountDTO.getAmount());
            transaction.setType("Credit");

            accountRepository.updateAccount(newBalance, account1.getAccount());
            transactionRepository.save(transaction);
            creditAccount.setCode("200");
            creditAccount.setMessage("Account deposit successful");
            creditAccount.setStatus("Successful");
            return creditAccount;
        }
        creditAccount.setMessage("The account number does not exist, please ensure you have created an account with us");
        return creditAccount;
    }
    /*
    returns true if the balance is greater than or equal
    to the amount to be debited from the account.
     */

    @Override
    public boolean debitWalletDuringPurchase(String acct, double amount) {
        Transaction transaction = new Transaction();
        Account account = accountRepository.findByAccount(acct).get();
        double balance = account.getBalance();
        if(balance >= amount){
            double newBalance = balance - amount;

            transaction.setAccount(account.getAccount());
            transaction.setId(null);
            transaction.setPeriod(LocalDateTime.now());
            transaction.setPhone(account.getPhone());
            transaction.setAmount(amount);
            transaction.setType("Debit");

            accountRepository.updateAccount(newBalance,acct);/*update the account with the current balance*/
            transactionRepository.save(transaction);/*Saves the debit transaction */

            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<?> getAccountByAccountNumber(String accoutNumber){
        Optional<Account> account = accountRepository.findByAccount(accoutNumber);
        CustomServiceProviders serviceProviders = new CustomServiceProviders();
        serviceProviders.setMessage("Sorry,there is no such account on our system");
        return account.isPresent()? ResponseEntity.ok(account.get()):ResponseEntity.badRequest().body(serviceProviders);
    }
}
