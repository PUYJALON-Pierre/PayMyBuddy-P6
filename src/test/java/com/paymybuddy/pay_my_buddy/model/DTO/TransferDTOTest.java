package com.paymybuddy.pay_my_buddy.model.DTO;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;

@SpringBootTest
public class TransferDTOTest {

  @Test
  public void transferDtoHashCodeTest() {

    TransferDTO transfer = new TransferDTO();
    TransferDTO transfer2 = new TransferDTO();

    assertNotSame(transfer, transfer2);
    assertEquals(transfer.hashCode(), transfer2.hashCode());
  }

  @Test
  public void transferDtoToStringTest() {

    ToStringVerifier.forClass(TransferDTO.class).verify();
  }

}
