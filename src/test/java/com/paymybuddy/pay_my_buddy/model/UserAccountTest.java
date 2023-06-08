package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

@SpringBootTest
public class UserAccountTest {

  
  @Test
  public void userAccountHashCodeTest() {

    UserAccount newAccount = new UserAccount();
    newAccount.setEmail("bertrandblanc@gmail.com");
    newAccount.setOnlineStatus(true);
    newAccount.setPassword("bertrand");

    UserAccount newAccount2 = new UserAccount();
    newAccount2.setEmail("bertrandblanc@gmail.com");
    newAccount2.setOnlineStatus(true);
    newAccount2.setPassword("bertrand");



    assertNotSame(newAccount, newAccount2);
    assertEquals(newAccount.hashCode(), newAccount2.hashCode());
  }

  @Test
  public void userAccountToStringTest() {

    ToStringVerifier.forClass(UserAccount.class).verify();
  }
}
