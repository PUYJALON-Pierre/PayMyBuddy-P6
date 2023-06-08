package com.paymybuddy.pay_my_buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UserAccountServiceTest {

  @Autowired
  IUserAccountService iUserAccountService;

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @AfterEach
  public void clear() {

    userAccountRepository.deleteAll();
  }

  @Test
  public void createUserAccountTest() throws UserAccountException {
    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("harrybrown@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("harry"));

    iUserAccountService.createUserAccount(newUserAccount);

    assertEquals(iUserAccountService.findUserByEmail("harrybrown@gmail.com").getEmail(),
        "harrybrown@gmail.com");
    assertEquals(userAccountRepository.findAll().size(), 1);
  }

  @Test
  public void createUserAccountButEmailAlreadyExistTest() throws UserAccountException {

    try {
      UserAccount newUserAccount = new UserAccount();
      newUserAccount.setEmail("benbrown@gmail.com");
      newUserAccount.setPassword(passwordEncoder.encode("ben"));

      iUserAccountService.createUserAccount(newUserAccount);
      iUserAccountService.createUserAccount(newUserAccount);
    }

    catch (Exception e) {

      assertEquals(e.getMessage(), "An account has already registred with this Email");
    }

  }

  @Test
  public void updateUserAccountTest() throws UserAccountException {

    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("benbrown@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("ben"));
    iUserAccountService.createUserAccount(newUserAccount);

    String oldPassword = iUserAccountService.findUserByEmail("benbrown@gmail.com").getPassword();

    UserAccount userAccountToUpdate = iUserAccountService.findUserByEmail("benbrown@gmail.com");
    userAccountToUpdate.setPassword(passwordEncoder.encode("hermione"));

    iUserAccountService.updateUserAccount(userAccountToUpdate);

    assertNotEquals(iUserAccountService.findUserByEmail("benbrown@gmail.com").getPassword(),
        oldPassword);
    // assertEquals(iUserAccountService.findUserByEmail("benbrown@gmail.com").getPassword(),
    // "hermione");
  }

  @Test
  public void updateUserAccountWithBadInfosTest() throws UserAccountException {

    try {
      UserAccount userAccountToUpdate = new UserAccount();
      userAccountToUpdate.setEmail("bademail@gmail.com");

      iUserAccountService.updateUserAccount(userAccountToUpdate);
    }

    catch (Exception e) {

      assertEquals(e.getMessage(), "User Account to update not founded");
    }
  }

  @Transactional @Test
  public void deleteUserAccountTest() throws UserAccountException {

    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("harrybrown@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("harry"));

    iUserAccountService.createUserAccount(newUserAccount);

    String emailToDelete = "harrybrown@gmail.com";

    iUserAccountService.deleteUserAccountByEmail(emailToDelete);

    assertThat((userAccountRepository.findByEmail(emailToDelete)).isEmpty());
    assertEquals(userAccountRepository.findAll().size(), 0);
  }

  @Test
  public void findByUserEmailTest() throws UserAccountException {

    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("jeanbolt@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("jean"));

    iUserAccountService.createUserAccount(newUserAccount);
    String emailToFind = "jeanbolt@gmail.com";

    iUserAccountService.findUserByEmail(emailToFind);

    assertEquals(iUserAccountService.findUserByEmail(emailToFind).getEmail(), "jeanbolt@gmail.com");

  }

}
