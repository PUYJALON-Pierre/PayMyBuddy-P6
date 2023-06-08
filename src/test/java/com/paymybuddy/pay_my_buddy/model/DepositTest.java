package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DepositTest {

  
  @Test
  public void depositHashCodeTest() {

 Deposit deposit = new Deposit();
 Deposit deposit2 = new Deposit();



    assertNotSame(deposit, deposit2);
    assertEquals(deposit2.hashCode(), deposit2.hashCode());
  }

}
