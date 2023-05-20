package com.paymybuddy.pay_my_buddy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

  UserAccountRepository userAccountRepository;

  public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
    super();
    this.userAccountRepository = userAccountRepository;
  }

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  
  @Override
  public UserAccount createUserAccount(UserAccount userAccount)throws UserAccountException{

    // checking if mail already exist
    if (userAccountRepository.findByEmail(userAccount.getEmail()).isPresent()) {
      throw new UserAccountException("An account has already registred with this Email");
     
    } else {
      userAccountRepository.save(userAccount);
      return userAccount;
    }

  }
  


  @Override
  public UserAccount updateUserAccount(UserAccount userAccount) throws UserAccountException {

   
    if (userAccountRepository.findByEmail(userAccount.getEmail()).isPresent()) {

      UserAccount userAccountToUpdate = userAccountRepository.findByEmail(userAccount.getEmail()).get();
    userAccountToUpdate.setPassword(passwordEncoder.encode(userAccount.getPassword()));
  

    userAccountRepository.save(userAccountToUpdate);

    return userAccountToUpdate;}
    
    
    else {
      throw new UserAccountException ("User Account to update not founded");}
    }
  

  
  @Override
  public void deleteUserAccountByEmail(String email) {
    userAccountRepository.deleteByEmail(email);

  }

  @Override
  public UserAccount findUserByEmail(String email) {
    return userAccountRepository.findByEmail(email).get();
  }

}
