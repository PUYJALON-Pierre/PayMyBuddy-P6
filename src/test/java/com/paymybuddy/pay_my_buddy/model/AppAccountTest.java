package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

@SpringBootTest
public class AppAccountTest {

  @Test
  public void appAccountHashCodeTest() {

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);

    AppAccount newAppAccount2 = new AppAccount();
    newAppAccount2.setBalance(0);



    assertNotSame(newAppAccount, newAppAccount2);
    assertEquals(newAppAccount.hashCode(), newAppAccount2.hashCode());
  }

  @Test
  public void appAccountToStringTest() {

    ToStringVerifier.forClass(AppAccount.class).verify();
  }
}
