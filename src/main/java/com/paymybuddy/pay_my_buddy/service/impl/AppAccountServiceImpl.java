package com.paymybuddy.pay_my_buddy.service.impl;

import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.service.IAppAccountService;

@Service
public class AppAccountServiceImpl implements IAppAccountService {

  AppAccountRepository appAccountRepository;

  public AppAccountServiceImpl(AppAccountRepository appAccountRepository) {
    super();
    this.appAccountRepository = appAccountRepository;
  }

  @Override
  public AppAccount createAppAccount(AppAccount appAccount) throws UserAccountException {

    // Checking if account already exist
    if (appAccountRepository.findById(appAccount.getAppAccountID()).isEmpty()) {

      appAccountRepository.save(appAccount);
    }
    else {
      throw new UserAccountException("This account already exist");
    }
    return appAccount;

  }

  @Override
  public AppAccount updateAppAccount(AppAccount appAccount) throws UserAccountException {

    AppAccount appAccountToUpdate = new AppAccount();

    // Checking if account already exist
    if (appAccountRepository.findById(appAccount.getAppAccountID()).isPresent()) {

      appAccountToUpdate.setAppAccountID(appAccount.getAppAccountID());
      appAccountToUpdate.setBalance(appAccount.getBalance());
      return appAccountRepository.save(appAccountToUpdate);
    } else {
      throw new UserAccountException("App Account to update not founded");
    }
  }

  @Override
  public void deleteAppAccountById(int appAccountId) {
    appAccountRepository.deleteById(appAccountId);
  }

  @Override
  public AppAccount getAppAccountById(int id) {
    return appAccountRepository.findById(id).get();
  }

}
