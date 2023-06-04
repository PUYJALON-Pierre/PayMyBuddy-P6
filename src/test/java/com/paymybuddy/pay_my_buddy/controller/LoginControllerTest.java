package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  
  
  
  
}
