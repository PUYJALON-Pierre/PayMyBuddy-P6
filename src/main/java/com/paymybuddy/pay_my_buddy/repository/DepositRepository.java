package com.paymybuddy.pay_my_buddy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;

/**
 * Repository of Deposits in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

  /**
   * Find all deposits by page from a User
   * 
   * @param sourceUser - User
   * @param page - Pageable
   * @return Page of Deposit
   */
  public Page<Deposit> findBySourceUser(User sourceUser, Pageable page);

  /**
   * Find all deposits by page from a BankAccountIBAN
   * 
   * @param iban - int
   * @param page - Pageable
   * @return Page of Deposit
   */
  public Page<Deposit> findByBankAccountIBAN(int iban, Pageable page);

  /**
   * Find a deposit by his id
   * 
   * @param depositId - int
   * @return Deposit
   */
  public Optional<Deposit> findById(int depositId);

  /**
   * Delete a deposit by SourceUser
   * 
   * @param sourceUser - user
   */
  public void deleteBySourceUser(User sourceUser);

}
