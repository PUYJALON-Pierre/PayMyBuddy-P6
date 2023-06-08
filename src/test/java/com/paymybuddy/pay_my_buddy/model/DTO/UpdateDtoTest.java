package com.paymybuddy.pay_my_buddy.model.DTO;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

import com.paymybuddy.pay_my_buddy.DTO.UpdateDTO;

@SpringBootTest
public class UpdateDtoTest {

  @Test
  public void updateDtoHashCodeTest() {

    UpdateDTO update = new UpdateDTO();
    UpdateDTO update2 = new UpdateDTO();

    assertNotSame(update, update2);
    assertEquals(update.hashCode(), update2.hashCode());
  }

  @Test
  public void updateDtoToStringTest() {

    ToStringVerifier.forClass(UpdateDTO.class).verify();
  }
}
