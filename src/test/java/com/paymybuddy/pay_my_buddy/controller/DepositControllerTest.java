package com.paymybuddy.pay_my_buddy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;

@WebMvcTest(controllers = DepositControllerTest.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class DepositControllerTest {

  
  @Autowired
  private MockMvc mockMvc;
  
  
  @Test
  public void getViewDepositPageModelTest() throws Exception {

    mockMvc.perform(get("/deposit")).andExpect(status().isOk());
  }
  
  @Test
  public void addDepositTest() throws Exception {

    DepositDTO depositDTO = new DepositDTO();
    depositDTO.setAmount(120);
    depositDTO.setIban("FR332500000550");
    depositDTO.setCurrency("$");
    depositDTO.setDescription("first");
    
    mockMvc.perform(get("/deposit").content(new ObjectMapper().writeValueAsString(depositDTO))
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
  }
  
  
  
}
