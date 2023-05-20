package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.controller.auth.AuthenticationResponse;
import com.paymybuddy.pay_my_buddy.controller.auth.AuthenticationService;

@SpringBootTest
public class AuthenticationServiceTest {

  @Autowired
  AuthenticationService authenticationService;
  
  
  @Test
  public void registerTest() {
    
    RegisterDTO registerDTO = new RegisterDTO();
    
    registerDTO.setFirstName("Shanks");
    registerDTO.setLastName("Murray");
    registerDTO.setBirthdate(new Date());
    registerDTO.setEmail("shanksmurray@gmail.com");
    registerDTO.setPassword("shanks");
    
    
    
    AuthenticationResponse response = authenticationService.register(registerDTO);
    
    
    assertNotNull( response.getToken());
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
