package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;

@SpringBootTest
public class MyUserDetailsServiceTest {

  @Autowired
  MyUserDetailsService myUserDetailsService;
  
  @Autowired
  IUserService iUserService;

  @Test
  public void loadUserByUsernameTest() {
    String mail = "jeanbolt@gmail.com";
   UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(mail);
    
    assertEquals("jeanbolt@gmail.com", userToRetrieve.getUsername() );
    
  }
  
  
  
  
  
  
  @Test
  public void registerTest() throws UserAccountException {
 RegisterDTO registerDTO = new RegisterDTO();
 
 registerDTO.setFirstname("Yves");
 registerDTO.setLastname("Lelong");
 registerDTO.setBirthdate(new Date());
 registerDTO.setEmail("yveslelong@gmail.com");
 registerDTO.setPassword("yves");
 
 //when 
 myUserDetailsService.registerUser(registerDTO);
 
 assertEquals(iUserService.getUsersList().size(), 5 );
  }
  
  
  @Test
  public void registerTestEmailAlreadyExist() throws UserAccountException {
 RegisterDTO registerDTO = new RegisterDTO();
 
 registerDTO.setFirstname("Yves");
 registerDTO.setLastname("Lelong");
 registerDTO.setBirthdate(new Date());
 registerDTO.setEmail("yveslelong@gmail.com");
 registerDTO.setPassword("yves");
 
 //when 
 try {
   //when
   myUserDetailsService.registerUser(registerDTO);}
  catch (Exception e) {
   assertEquals(e.getMessage(), "An account has already registred with this Email");
 }
  }
  
}
