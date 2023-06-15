package com.paymybuddy.pay_my_buddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;




@WebMvcTest(controllers = HomeController.class)
@WithMockUser(username="duke", roles={"USER"}, password="duke")
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
 
  
  @Test
  public void getViewHomePageModelTest() throws Exception {

    mockMvc.perform(get("/home")).andExpect(status().isOk());
  }
  
  @Test
  public void getViewDefaultPageModelTest() throws Exception {

    mockMvc.perform(get("/")).andExpect(status().isFound());
  }
}
