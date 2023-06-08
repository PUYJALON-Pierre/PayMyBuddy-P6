package com.paymybuddy.pay_my_buddy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;

/**
 * Repository of Transfers in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Integer> {

  /**
   * Find all Transfers by page from a User
   * 
   * @param sourceUser - User
   * @param page - Pageable
   * @return Page of Transfer
   */
  public Page<Transfert> findBySourceUser(User sourceUser, Pageable page);

  /**
   * Delete a transfer by SourceUser
   * 
   * @param sourceUser - user
   */
  public void deleteBySourceUser(User sourceUser);

  /**
   * Find a Transfer by his id
   * 
   * @param transferId - int
   * @return Transfer
   */
  public Optional<Transfert> findById(int transferId);

  /**
   * Find all Transfers by page from a User
   * 
   * @param userRecipient - User
   * @param page - Pageable
   * @return Page of Transfer
   */
  public Page<Transfert> findByRecipient(User userRecipient, Pageable page);

  /**
   * Find all Transfers by page from a User or another User
   * 
   * @param sourceUser - User
   * @param userRecipient - User
   * @param page - Pageable
   * @return Page of Transfer
   */
  public Page<Transfert> findBySourceUserOrRecipient(User sourceUser, User recipient,
      Pageable page);

  
  
}
