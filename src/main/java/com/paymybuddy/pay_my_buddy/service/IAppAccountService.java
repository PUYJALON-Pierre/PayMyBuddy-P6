package com.paymybuddy.pay_my_buddy.service;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;

/**
 * Interface for services operations concerning AppAccount
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
public interface IAppAccountService {

  /**
   * Create an AppAccount
   *
   * @param appAccount - AppAccount
   * @return AppAccount
   */
  public AppAccount createAppAccount(AppAccount appAccount) throws UserAccountException;

  /**
   * Update an AppAccount
   *
   * @param appAccount - AppAccount
   * @return AppAccount
   */
  public AppAccount updateAppAccount(AppAccount appAccount) throws UserAccountException;

  /**
   * Delete an AppAccount by id
   *
   * @param appAccountId - int
   */
  public void deleteAppAccountById(int appAccountId);

  /**
   * Find an AppAccount by his id
   *
   * @param id - int
   * @return AppAccount
   */
  public AppAccount getAppAccountById(int id);

}
