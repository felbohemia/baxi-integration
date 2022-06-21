package com.atamie.baxiservices.repositories.accountrepositories;

import com.atamie.baxiservices.domain.account.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {
    /*
       .............JPQL (java persistence query language)............
     */
    @Query(value = "select * from account U where U.email = ?1 and U.password = ?2",nativeQuery = true)
    Optional<Account> findByEmailAndPassword(final String email, final String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update account U set U.balance = :balance where U.account = :account",nativeQuery = true)
    void updateAccount(@Param("balance") double balance, @Param("account") String account);

    Optional<Account> findByAccount(String account);

}
