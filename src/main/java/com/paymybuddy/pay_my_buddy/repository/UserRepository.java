package com.paymybuddy.pay_my_buddy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;

/**
 * Repository of Users in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find all Users by page
   *
   * @param page - Pageable
   * @return Page of User
   */
  public Page<User> findAll(Pageable page);

  /**
   * Delete a user by id
   *
   * @param id - int
   * @return User
   */
  public Optional<User> deleteById(int id);

  /**
   * Find a user by his UserAccount
   *
   * @param userAccount - UserAccount
   * @return User
   */
  public Optional<User> findByUserAccount(UserAccount userAccount);

}
