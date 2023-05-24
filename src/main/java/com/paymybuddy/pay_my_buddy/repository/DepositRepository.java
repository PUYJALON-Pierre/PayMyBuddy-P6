package com.paymybuddy.pay_my_buddy.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;


@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer>{

  
  public Page <Deposit> findBySourceUser(User sourceUser, Pageable page);
  
  public Page <Deposit> findByBankAccountIBAN(int iban, Pageable page);
  
  public Optional<Deposit> findById(int depositId);

  public void deleteBySourceUser(User sourceUser);

}
