package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;

import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class MyUserDetailsServiceTest {

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  IUserService iUserService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  AppAccountRepository accountRepository;

  @AfterAll
  public void clear() {
    
    // clear data
    userRepository.deleteAll();
    userAccountRepository.deleteAll();
    accountRepository.deleteAll();
    
  }
  
  @Test
  public void registerTest() throws UserAccountException {
    RegisterDTO registerDTO = new RegisterDTO();

    registerDTO.setFirstname("Yves");
    registerDTO.setLastname("Lelong");
    registerDTO.setBirthdate(new Date());
    registerDTO.setEmail("yveslelong@gmail.com");
    registerDTO.setPassword("yves");

    // when
    myUserDetailsService.registerUser(registerDTO);

    assertEquals(iUserService.getUsersList().size(), 1);
  }

  @Test
  public void registerTestEmailAlreadyExist() throws UserAccountException {
    RegisterDTO registerDTO = new RegisterDTO();

    registerDTO.setFirstname("Yves");
    registerDTO.setLastname("Lelong");
    registerDTO.setBirthdate(new Date());
    registerDTO.setEmail("yveslelong@gmail.com");
    registerDTO.setPassword("yves");

    // when
    try {
      // when
      myUserDetailsService.registerUser(registerDTO);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "An account has already registred with this Email");
    }
  }

  @Test
  public void loadUserByUsernameTest() {
    String mail = "yveslelong@gmail.com";
    UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(mail);

    assertEquals("yveslelong@gmail.com", userToRetrieve.getUsername());

  

  }

}
