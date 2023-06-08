package com.paymybuddy.pay_my_buddy.model.DTO;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;


@SpringBootTest
public class DepositDtoTest {

  

  @Test
  public void depositDTOHashCodeTest() {

  DepositDTO deposit = new DepositDTO();
  deposit.setAmount(0);
  deposit.setCurrency("$");
  deposit.setDescription("");
  deposit.setIban("aaaa");
  
  DepositDTO deposit2 = new DepositDTO();
  deposit2.setAmount(0);
  deposit2.setCurrency("$");
  deposit2.setDescription("");
  deposit2.setIban("aaaa");


    assertNotSame(deposit, deposit2);
    assertEquals(deposit.hashCode(), deposit2.hashCode());
  }

  @Test
  public void depositDTOToStringTest() {

    ToStringVerifier.forClass(DepositDTO.class).verify();
  }
  
  
  
}
