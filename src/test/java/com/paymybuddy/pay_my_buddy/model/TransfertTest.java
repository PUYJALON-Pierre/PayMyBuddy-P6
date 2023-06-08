package com.paymybuddy.pay_my_buddy.model;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TransfertTest {
  
  @Test
  public void transfertHashCodeTest() {

    Transfert transfert = new Transfert();
    transfert.setAmount(100);
    transfert.setCurrency("$");
    transfert.setDate(new Date());
    transfert.setDescription("first");
    transfert.setFee(0);
  
    
    
    Transfert transfert2 = new Transfert();
    transfert2.setAmount(100);
    transfert2.setCurrency("$");
    transfert2.setDate(new Date());
    transfert2.setDescription("first");
    transfert2.setFee(0);
    
    assertNotSame(transfert, transfert2);
    assertEquals(transfert.hashCode(), transfert2.hashCode());
  }


  
}
