package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.TransfertRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class TransfertServiceTest {

  @Autowired
  ITransfertService iTransfertService;

  @Autowired
  TransfertRepository transfertRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  IUserService iUserService;

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  AppAccountRepository accountRepository;

  private User user1;

  private User user2;

  private Transfert transfert1;

  private Transfert transfert2;

  @BeforeEach
  public void init() throws UserAccountException {

    // clearing data
    userRepository.deleteAll();
    accountRepository.deleteAll();
    userAccountRepository.deleteAll();
    transfertRepository.deleteAll();

    // Create new Users
    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(1000);
    accountRepository.save(newAppAccount);

    UserAccount newAccount = new UserAccount();
    newAccount.setEmail("bertrandblanc@gmail.com");
    newAccount.setOnlineStatus(true);
    newAccount.setPassword("bertrand");
    userAccountRepository.save(newAccount);

    user1 = new User("Bertrand", "Blanc", new Date(), newAccount, newAppAccount);
    iUserService.saveUser(user1);

    AppAccount newAppAccount2 = new AppAccount();
    newAppAccount2.setBalance(1000);
    accountRepository.save(newAppAccount2);

    UserAccount newAccount2 = new UserAccount();
    newAccount2.setEmail("leonblanc@gmail.com");
    newAccount2.setOnlineStatus(true);
    newAccount2.setPassword("leon");
    userAccountRepository.save(newAccount2);

    user2 = new User("Leon", "Blanc", new Date(), newAccount2, newAppAccount2);
    iUserService.saveUser(user2);

    // creating transferts
    transfert1 = new Transfert();
    transfert1.setAmount(100);
    transfert1.setCurrency("$");
    transfert1.setDate(new Date());
    transfert1.setDescription("first");
    transfert1.setFee(0);
    transfert1.setRecipient(user2);
    transfert1.setSourceUser(user1);

    iTransfertService.saveTransfert(transfert1);

    transfert2 = new Transfert();
    transfert2.setAmount(50);
    transfert2.setCurrency("$");
    transfert2.setDate(new Date());
    transfert2.setDescription("second");
    transfert2.setFee(0);
    transfert2.setRecipient(user2);
    transfert2.setSourceUser(user1);

    iTransfertService.saveTransfert(transfert2);
  }

  @AfterAll
  public void clear() {
    // clear data
    userRepository.deleteAll();
    accountRepository.deleteAll();
    userAccountRepository.deleteAll();
    transfertRepository.deleteAll();
  }

  @Transactional @Test
  public void getTransfertsTest() {

    // when
    iTransfertService.getTransferts();

    assertEquals(iTransfertService.getTransferts().size(), 2);
    assertEquals(iTransfertService.getTransferts().get(0).getAmount(), 100);
    assertEquals(iTransfertService.getTransferts().get(0).getDescription(), "first");
    assertEquals(iTransfertService.getTransferts().get(0).getSourceUser(), user1);
    assertEquals(iTransfertService.getTransferts().get(0).getRecipient(), user2);

    assertEquals(iTransfertService.getTransferts().get(1).getAmount(), 50);
    assertEquals(iTransfertService.getTransferts().get(1).getDescription(), "second");
    assertEquals(iTransfertService.getTransferts().get(1).getSourceUser(), user1);
    assertEquals(iTransfertService.getTransferts().get(1).getRecipient(), user2);
  }

  @Test
  public void getTransfertBySourceUserTest() {

    User user = iUserService.getUserById(user1.getUserID());

    // when
    Page<Transfert> transfer = iTransfertService.getTransfertsBySourceUser(user, 0);

    assertEquals(transfer.getNumberOfElements(), 2);
    assertEquals(transfer.getContent().get(0).getAmount(), 50);
    assertEquals(transfer.getContent().get(1).getAmount(), 100);
  }

  @Test
  public void getTransfertByIdTest() {

    // when
    assertEquals(iTransfertService.getTransfertById(transfert1.getId()).getAmount(), 100);
    assertEquals(iTransfertService.getTransfertById(transfert1.getId()).getDescription(), "first");
    assertEquals(iTransfertService.getTransfertById(transfert2.getId()).getAmount(), 50);
    assertEquals(iTransfertService.getTransfertById(transfert2.getId()).getDescription(), "second");
  }

  @Transactional @Test
  public void getTransfertBetweenUserTest() {

    // when
    Page<Transfert> transfer = iTransfertService.getTransfertsBetweenAnyUsers(user1, user2, 0);

    assertEquals(transfer.getContent().get(0).getAmount(), 100);
    assertEquals(transfer.getContent().get(0).getDescription(), "first");
    assertEquals(transfer.getContent().get(0).getSourceUser(), user1);
    assertEquals(transfer.getContent().get(0).getRecipient(), user2);
    assertEquals(transfer.getContent().get(1).getAmount(), 50);
    assertEquals(transfer.getContent().get(1).getDescription(), "second");
    assertEquals(transfer.getNumberOfElements(), 2);

  }

  @Test
  public void saveTransfertTest() throws UserBalanceException, UserAccountException {

    TransferDTO newTransfer = new TransferDTO();

    newTransfer.setRecipient("leonblanc@gmail.com");
    newTransfer.setAmount(15);
    newTransfer.setCurrency("$");
    newTransfer.setDescription("deposit");

    // when
    iTransfertService.createTransfert(user1, newTransfer);

    assertEquals(iTransfertService.getTransferts().size(), 3);

  }

  @Test
  public void saveTransfertFailUsernameTest() throws UserBalanceException, UserAccountException {

    TransferDTO newTransfer = new TransferDTO();

    newTransfer.setRecipient("sophie@gmail.com");
    newTransfer.setAmount(15);
    newTransfer.setCurrency("$");
    newTransfer.setDescription("deposit");

    try {
      // when
      iTransfertService.createTransfert(user1, newTransfer);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "Recipient of this transfer cannot be find");
    }

  }

  @Test
  public void saveTransfertFailBalanceTest() throws UserBalanceException, UserAccountException {

    TransferDTO newTransfer = new TransferDTO();

    newTransfer.setRecipient("leonblanc@gmail.com");
    newTransfer.setAmount(1500000);
    newTransfer.setCurrency("$");
    newTransfer.setDescription("deposit");

    try {
      // when
      iTransfertService.createTransfert(user1, newTransfer);
    } catch (Exception e) {
      assertEquals(e.getMessage(),
          "You dont have enough money on your app account to make this transfer");
    }

  }

  @Test
  public void deleteTransfertByIdTest() {

    int transferId = transfert1.getId();

    // when
    iTransfertService.deleteTransfertById(transferId);
    assertEquals(iTransfertService.getTransferts().size(), 1);
  }

  @Transactional @Test
  public void deleteTransfertBySourceUserTest() {

    // when
    iTransfertService.deleteTransfertBySourceUser(user1);
    assertEquals(iTransfertService.getTransferts().size(), 0);

  }

}
