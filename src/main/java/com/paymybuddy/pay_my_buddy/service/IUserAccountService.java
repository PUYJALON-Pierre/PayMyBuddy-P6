package com.paymybuddy.pay_my_buddy.service;

import com.paymybuddy.pay_my_buddy.model.UserAccount;

public interface IUserAccountService {

 
 
  public UserAccount createUserAccount(UserAccount userAccount);
  
  public UserAccount updateUserAccount(UserAccount userAccount);
  
  public void deleteUserAccountByEmail(String email);
  
  public UserAccount findUserByEmail(String email);
  
  
  
}
