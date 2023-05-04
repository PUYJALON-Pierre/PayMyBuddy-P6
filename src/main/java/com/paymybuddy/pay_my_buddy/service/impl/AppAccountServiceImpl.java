package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
  public AppAccount createAppAccount(AppAccount appAccount) {

    // checking if account already exist
    if (appAccountRepository.findById(appAccount.getAppAccountID()).get() != appAccount) {

      appAccountRepository.save(appAccount);
    }

    return appAccount;

  }

  @Override
  public AppAccount updateAppAccount(AppAccount appAccount) {

    Optional<AppAccount> appAccountToUpdate = appAccountRepository
        .findById(appAccount.getAppAccountID());

    if (appAccountToUpdate != null) {

      appAccountToUpdate.get().setAppAccountID(appAccount.getAppAccountID());
      appAccountToUpdate.get().setBalance(appAccount.getBalance());
      appAccountRepository.save(appAccountToUpdate.get());
    }
    return appAccount;
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
