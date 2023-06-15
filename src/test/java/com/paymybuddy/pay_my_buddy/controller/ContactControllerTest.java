package com.paymybuddy.pay_my_buddy.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.service.MyUserDetailsServiceTest;


@WebMvcTest(controllers = ContactController.class)
@WithMockUser(username="duke", roles={"USER"}, password="duke")
public class ContactControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  
  @Test
  public void getViewContactPageModelTest() throws Exception {

    mockMvc.perform(get("/contact")).andExpect(status().isOk());
  }
  
}
