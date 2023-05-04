package com.paymybuddy.pay_my_buddy.service;

import com.paymybuddy.pay_my_buddy.model.AppAccount;

public interface IAppAccountService {

  
  
  public AppAccount createAppAccount(AppAccount appAccount);
  
  public AppAccount updateAppAccount(AppAccount appAccount);
  
  public void deleteAppAccountById(int appAccountId);
  
  public AppAccount getAppAccountById(int id);
  
}
