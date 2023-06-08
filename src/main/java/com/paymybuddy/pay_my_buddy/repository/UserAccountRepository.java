package com.paymybuddy.pay_my_buddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.UserAccount;

/**
 * Repository of UserAccount in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

  /**
   * Find a UserAccount by his id
   * 
   * @param id - int
   * @return UserAccount
   */
  public Optional<UserAccount> findById(int id);

  /**
   * Find a UserAccount by his email
   * 
   * @param email - String
   * @return UserAccount
   */
  public Optional<UserAccount> findByEmail(String email);

  /**
   * Delete a UserAccount by his id
   * 
   * @param id - int
   */
  public Optional<UserAccount> deleteById(int id);

  /**
   * Delete a UserAccount by his email
   * 
   * @param email - String
   * @return UserAccount
   */
  public Optional<UserAccount> deleteByEmail(String email);

  
  public List<UserAccount> findByEmailContains(String email);
  
}
