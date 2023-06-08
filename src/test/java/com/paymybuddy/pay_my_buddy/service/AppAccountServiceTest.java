package com.paymybuddy.pay_my_buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;

@SpringBootTest
public class AppAccountServiceTest {

  @Autowired
  IAppAccountService iAppAccountService;

  @Autowired
  AppAccountRepository appAccountRepository;

  @AfterEach
  public void clear() {

    appAccountRepository.deleteAll();
  }

  @Test
  public void createAppAccountTest() throws UserAccountException {
    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(170);

    iAppAccountService.createAppAccount(newAppAccount);

    assertEquals(appAccountRepository.findAll().size(), 1);

  }

  @Test
  public void createAppAccountWithIdAlreadyExistingTest() throws UserAccountException {

    try {

      AppAccount newAppAccount1 = new AppAccount();
      newAppAccount1.setBalance(16);
      iAppAccountService.createAppAccount(newAppAccount1);
      iAppAccountService.createAppAccount(newAppAccount1);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "This account already exist");
    }
  }

  @Test
  public void updateAppAccountTest() throws UserAccountException {
    AppAccount newAppAccount2 = new AppAccount();
    newAppAccount2.setBalance(16);
    iAppAccountService.createAppAccount(newAppAccount2);

    AppAccount updateAppAccount = new AppAccount();
    updateAppAccount.setBalance(165);
    updateAppAccount.setAppAccountID(newAppAccount2.getAppAccountID());

    iAppAccountService.updateAppAccount(updateAppAccount);

    assertEquals(appAccountRepository.findById(newAppAccount2.getAppAccountID()).get().getBalance(),
        165);

  }

  @Test
  public void updateAppAccountWithBadIdTest() throws UserAccountException {

    try {

      AppAccount updateAppAccount = new AppAccount();

      updateAppAccount.setBalance(165);
      updateAppAccount.setAppAccountID(15);

      iAppAccountService.updateAppAccount(updateAppAccount);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "App Account to update not founded");

    }

  }

  @Test
  public void deleteAppAccountTest() throws UserAccountException {

    AppAccount newAppAccount3 = new AppAccount();
    newAppAccount3.setBalance(1658);
    iAppAccountService.createAppAccount(newAppAccount3);

    iAppAccountService.deleteAppAccountById(newAppAccount3.getAppAccountID());

    assertThat(appAccountRepository.findById(newAppAccount3.getAppAccountID()).isEmpty());

  }

  @Test
  public void getAppAccountByIdTest() throws UserAccountException {

    AppAccount newAppAccount4 = new AppAccount();
    newAppAccount4.setBalance(100);
    iAppAccountService.createAppAccount(newAppAccount4);

    AppAccount appAccountRetrieve = iAppAccountService
        .getAppAccountById(newAppAccount4.getAppAccountID());

    assertEquals(appAccountRetrieve.getBalance(), 100);

  }

}
