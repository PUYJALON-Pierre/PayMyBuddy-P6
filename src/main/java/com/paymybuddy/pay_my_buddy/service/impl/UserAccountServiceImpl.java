package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

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

  @Override
  public UserAccount createUserAccount(UserAccount userAccount) {

    // checking if mail already exist
    if (userAccountRepository.findByEmail(userAccount.getEmail()).get() == null) {

      userAccountRepository.save(userAccount);
      return userAccount;
    } else {
      return null;
    }

  }

  @Override
  public UserAccount updateUserAccount(UserAccount userAccount) {

    Optional<UserAccount> userAccountToUpdate = userAccountRepository
        .findByEmail(userAccount.getEmail());

    userAccountToUpdate.get().setUserAccountID(userAccount.getUserAccountID());
    userAccountToUpdate.get().setPassword(userAccount.getPassword());
    userAccountToUpdate.get().setLastConnection(userAccount.getLastConnection());

    userAccountRepository.save(userAccountToUpdate.get());

    return userAccountToUpdate.get();
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
