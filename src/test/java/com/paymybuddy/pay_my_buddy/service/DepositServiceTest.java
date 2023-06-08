package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.DepositRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class DepositServiceTest {

  @Autowired
  IDepositService iDepositService;

  @Autowired
  DepositRepository depositRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  AppAccountRepository appAccountRepository;

  @Autowired
  UserAccountRepository userAccountRepository;

  private User user;

  private Deposit deposit;

  @BeforeEach
  public void init() {

    // clearing data
    userRepository.deleteAll();
    userAccountRepository.deleteAll();
    userAccountRepository.deleteAll();
    depositRepository.deleteAll();
    appAccountRepository.deleteAll();
    ;

    // Creating User
    AppAccount appAccount = new AppAccount();
    appAccount.setBalance(100);
    appAccountRepository.save(appAccount);

    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("arnauldbord@gmail.com");
    userAccount.setLastConnection(new Date());
    userAccount.setOnlineStatus(true);
    userAccount.setPassword("yes");
    userAccountRepository.save(userAccount);

    user = new User();
    user.setAppAccount(appAccount);
    user.setBirthdate(new Date());
    user.setFirstname("Benoit");
    user.setLastname("Bord");
    user.setUserAccount(userAccount);
    user.setAppAccount(appAccount);
    user.setAppAccount(appAccount);

    userRepository.save(user);

    // Creating deposit
    DepositDTO depositDTO = new DepositDTO();
    depositDTO.setAmount(120);
    depositDTO.setIban("FR332500000550");
    depositDTO.setCurrency("$");
    depositDTO.setDescription("first");

    deposit = iDepositService.saveDeposit(user, depositDTO);

  }

  @AfterAll
  public void clear() {
    // clear data
    userRepository.deleteAll();
    userAccountRepository.deleteAll();
    userAccountRepository.deleteAll();
    depositRepository.deleteAll();
  }

  @Test
  public void getDepositsTest() {

    // when
    iDepositService.getDeposits();

    assertEquals(iDepositService.getDeposits().size(), 1);
    assertEquals(iDepositService.getDeposits().get(0).getAmount(), 120);
    assertEquals(iDepositService.getDeposits().get(0).getBankAccountIBAN(), "FR332500000550");
    assertEquals(iDepositService.getDeposits().get(0).getDescription(), "first");
  }

  @Transactional @Test
  public void getDepositsBySourceUserTest() {

    // when
    Page<Deposit> deposit = iDepositService.getDepositBySourceUser(user, 0);

    assertEquals(deposit.getContent().get(0).getAmount(), 120);
    assertEquals(deposit.getContent().get(0).getBankAccountIBAN(), "FR332500000550");
    assertEquals(deposit.getContent().get(0).getSourceUser(), user);
    assertEquals(deposit.getNumberOfElements(), 1);
  }

  @Test
  public void getDepositByIdTest() {

    // when
    assertEquals(iDepositService.getDepositById(deposit.getId()).get().getAmount(), 120);
    assertEquals(iDepositService.getDepositById(deposit.getId()).get().getBankAccountIBAN(),
        "FR332500000550");

  }

  @Test
  public void saveDepositTest() {

    DepositDTO newDeposit = new DepositDTO();

    newDeposit.setIban("FR45215475646");
    newDeposit.setAmount(150);
    newDeposit.setCurrency("$");
    newDeposit.setDescription("deposit");

    // when
    iDepositService.saveDeposit(user, newDeposit);

    assertEquals(iDepositService.getDeposits().size(), 2);

  }

  @Test
  public void saveWithdrawTest() throws UserBalanceException {

    DepositDTO newDeposit = new DepositDTO();

    newDeposit.setIban("FR45215475646");
    newDeposit.setAmount(150);
    newDeposit.setCurrency("$");
    newDeposit.setDescription("deposit");

    // when
    iDepositService.saveWithdraw(user, newDeposit);

    assertEquals(iDepositService.getDeposits().size(), 2);
  }

  @Test
  public void saveWithdrawFailTest() throws UserBalanceException {

    DepositDTO newDeposit = new DepositDTO();

    newDeposit.setIban("FR45215475646");
    newDeposit.setAmount(1500000);
    newDeposit.setCurrency("$");
    newDeposit.setDescription("deposit");

    try {
      // when
      iDepositService.saveWithdraw(user, newDeposit);
    } catch (Exception e) {
      assertEquals(e.getMessage(),
          "You dont have enough money on your app account to make this transfer");
    }
    assertEquals(iDepositService.getDeposits().size(), 1);
  }

  @Test
  public void deleteDepositByIdTest() {

    // when
    iDepositService.deleteDepositById(deposit.getId());
    assertEquals(iDepositService.getDeposits().size(), 0);
  }

}
