package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class MyUserDetailsServiceTest {

  @Autowired
  MyUserDetailsService myUserDetailsService;
  

  @Test
  public void loadUserByUsernameTest() {
    String mail = "jeanbolt@gmail.com";
   UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(mail);
    
    assertEquals("jeanbolt@gmail.com", userToRetrieve.getUsername() );
    
  }
  
  
  
  
  
  
  
  
  
}
