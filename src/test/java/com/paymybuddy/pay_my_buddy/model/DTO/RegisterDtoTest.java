package com.paymybuddy.pay_my_buddy.model.DTO;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jparams.verifier.tostring.ToStringVerifier;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;

@SpringBootTest
public class RegisterDtoTest {

  @Test
  public void registerDtoHashCodeTest() {

RegisterDTO register = new RegisterDTO();
RegisterDTO register2 = new RegisterDTO();


    assertNotSame(register, register2);
    assertEquals(register.hashCode(), register2.hashCode());
  }

  @Test
  public void registerDtoToStringTest() {

    ToStringVerifier.forClass(RegisterDTO.class).verify();
  }
  
  
}
