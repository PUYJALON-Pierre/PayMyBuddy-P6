package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

@SpringBootTest
public class UserTest {

  @Test
  public void userHashCodeTest() {

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);

    UserAccount newAccount = new UserAccount();
    newAccount.setEmail("bertrandblanc@gmail.com");
    newAccount.setOnlineStatus(true);
    newAccount.setPassword("bertrand");

    User user1 = new User("Bertrand", "Blanc", new Date(), newAccount, newAppAccount);
    User user2 = new User("Bertrand", "Blanc", new Date(), newAccount, newAppAccount);

    assertNotSame(user1, user2);
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  @Test
  public void userToStringTest() {

    ToStringVerifier.forClass(User.class).verify();
  }

}
