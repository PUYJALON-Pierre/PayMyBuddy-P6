package com.paymybuddy.pay_my_buddy.service;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.UserAccount;

/**
 * Interface for services operations concerning UserAccount
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
public interface IUserAccountService {

  /**
   * Create a UserAccount
   *
   * @param userAccount - UserAccount
   * @return UserAccount
   */
  public UserAccount createUserAccount(UserAccount userAccount) throws UserAccountException;

  /**
   * Update a UserAccount
   *
   * @param userAccount - UserAccount
   * @return UserAccount
   */
  public UserAccount updateUserAccount(UserAccount userAccount) throws UserAccountException;

  /**
   * Delete a UserAccount by email
   *
   * @param email - String
   */
  public void deleteUserAccountByEmail(String email);

  /**
   * Find a UserAccount by his email
   *
   * @param userAccount - UserAccount
   * @return UserAccount
   */
  public UserAccount findUserByEmail(String email);

}
