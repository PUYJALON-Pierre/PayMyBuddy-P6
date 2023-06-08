package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

@SpringBootTest
public class TransactionTest {

  @Test
  public void transactionHashCodeTest() {

    Transaction transaction = new Transaction();
    transaction.setAmount(10);
    transaction.setCurrency("$");
    transaction.setDate(new Date());
    transaction.setDescription("first");
    transaction.setFee(0);

    Transaction transaction2 = new Transaction();
    transaction2.setAmount(10);
    transaction2.setCurrency("$");
    transaction2.setDate(new Date());
    transaction2.setDescription("first");
    transaction2.setFee(0);

    assertNotSame(transaction, transaction2);
    assertEquals(transaction.hashCode(), transaction2.hashCode());
  }

  @Test
  public void transactionToStringTest() {

    ToStringVerifier.forClass(Transaction.class).verify();
  }

}
