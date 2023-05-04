package com.paymybuddy.pay_my_buddy.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Integer> {

  
  public Page <Transfert> findBySourceUser(User sourceUser, Pageable page);
  
  public void deleteBySourceUser(User sourceUser);
  
  public Optional<Transfert> findById(int transferId);
  
  public Page <Transfert> findByRecipient(User userRecipient, Pageable page);
  
  public Page <Transfert> findBySourceUserOrRecipient (User sourceUser, User recipient, Pageable page);
  
  
}
