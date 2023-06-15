package com.paymybuddy.pay_my_buddy.controller;




import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;


import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IUserAccountService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

@WebMvcTest(controllers = ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfileControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUserService iUserService;

  @MockBean
  private IUserAccountService iUserAccountService;

  @MockBean
  private UserRepository userRepository;

  
  User user;
  User friend;

  @BeforeEach
  public void setup() {

    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("testuser@email.com");
    userAccount.setPassword("password");

    user = new User();
    user.setUserAccount(userAccount);

    UserAccount userAccount1 = new UserAccount();
    userAccount1.setEmail("testuser1@email.com");
    userAccount1.setPassword("password");

    friend = new User();
    friend.setUserAccount(userAccount);
    friend.setUserID(1);
 
  }



  @Test
  public void addFriendTest() throws Exception {
    
    when(iUserService.getConnectedUser()).thenReturn(user);
    when(iUserService.getUserById(1)).thenReturn(friend);
  
    mockMvc.perform(post("/addfriend").param("id", "1")).andExpect(redirectedUrl("profile")).andExpect(status().is3xxRedirection());
  }

  
  @Test
  public void deleteFriendTest() throws Exception {
    
    when(iUserService.getConnectedUser()).thenReturn(user);
    when(iUserService.getUserById(1)).thenReturn(friend);
  
    mockMvc.perform(post("/deletefriend").param("id", "1")).andExpect(redirectedUrl("profile")).andExpect(status().is3xxRedirection());  
  }
  
  
  
}
