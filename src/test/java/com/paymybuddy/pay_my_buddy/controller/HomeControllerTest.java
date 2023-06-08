package com.paymybuddy.pay_my_buddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.config.MyUserDetailsConfigTest;



@WebMvcTest(controllers = HomeControllerTest.class)
@Import(MyUserDetailsConfigTest.class)
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
 
  
  @Test
  public void getViewHomePageModelTest() throws Exception {

    mockMvc.perform(get("/home")).andExpect(status().isOk());
  }
  

}
