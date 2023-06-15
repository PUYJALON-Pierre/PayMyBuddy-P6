package com.paymybuddy.pay_my_buddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

@WebMvcTest(controllers = RegisterController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RegisterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
 private MyUserDetailsService myUserDetailsService;
  
  
  @Test
  public void getViewRegisterPageModelTest() throws Exception {
    mockMvc.perform(get("/registration")).andExpect(status().isOk());
  }

  @Test
  public void registerUserTest() throws Exception {

  RegisterDTO register = new RegisterDTO();
  register.setBirthdate(new Date());
  register.setEmail("test@gmail.com");
  register.setFirstname("test");
  register.setLastname("test");
  register.setPassword("test");
  
  when(myUserDetailsService.registerUser(register)).thenReturn(new User());

    mockMvc
        .perform(post("/registration").param("birthdate", "2011-01-01").param("email", "test@gmail.com")
            .param("firstname", "test").param("lastname", "test").param("password", "test"))
        .andExpect(redirectedUrl("/login")).andExpect(status().is3xxRedirection());
  }

}
