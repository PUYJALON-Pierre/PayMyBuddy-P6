package com.paymybuddy.pay_my_buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;

@SpringBootTest @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AppAccountServiceTest {

  @Autowired
  IAppAccountService iAppAccountService;

  @Autowired
  AppAccountRepository appAccountRepository;

  
  @Test
  public void createAppAccount() throws UserAccountException {
    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);

    iAppAccountService.createAppAccount(newAppAccount);

    assertEquals(appAccountRepository.findAll().size(), 5);

  }

  @Test
  public void createAppAccountWithIdAlreadyExisting() throws UserAccountException {

    try {
      AppAccount newAppAccount = new AppAccount();
      newAppAccount.setBalance(0);
      iAppAccountService.createAppAccount(newAppAccount);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "This account already exist");
    }
  }

  @Test
  public void updateAppAccount() throws UserAccountException {
    AppAccount updateAppAccount = new AppAccount();
    updateAppAccount.setBalance(165);
    updateAppAccount.setAppAccountID(4);

    iAppAccountService.updateAppAccount(updateAppAccount);

    assertEquals(appAccountRepository.findById(4).get().getBalance(), 165);

  }

  @Test
  public void updateAppAccountWithBadId() throws UserAccountException {

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
  public void deleteAppAccount() {

    iAppAccountService.deleteAppAccountById(3);

    assertThat(appAccountRepository.findById(3).isEmpty());

  }

  @Test
  public void getAppAccountById() {

    iAppAccountService.getAppAccountById(4);

    assertThat(appAccountRepository.findById(4).isEmpty());

  }

}
