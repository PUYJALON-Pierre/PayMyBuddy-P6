package com.paymybuddy.pay_my_buddy.service;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;

public interface IAppAccountService {

  
  
  public AppAccount createAppAccount(AppAccount appAccount)throws UserAccountException ;
  
  public AppAccount updateAppAccount(AppAccount appAccount)throws UserAccountException ;
  
  public void deleteAppAccountById(int appAccountId);
  
  public AppAccount getAppAccountById(int id);
  
}
