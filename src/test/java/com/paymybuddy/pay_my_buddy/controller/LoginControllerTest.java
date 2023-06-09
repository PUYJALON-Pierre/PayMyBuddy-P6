package com.paymybuddy.pay_my_buddy.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;




@WebMvcTest(controllers = LoginController.class)

public class LoginControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  
  @Test
  public void getViewLoginPageModelTest() throws Exception {

    mockMvc.perform(get("/login")).andExpect(status().isOk());
  
    
  }
  
}
