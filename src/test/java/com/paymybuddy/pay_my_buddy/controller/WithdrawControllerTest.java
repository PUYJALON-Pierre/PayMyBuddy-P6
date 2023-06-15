package com.paymybuddy.pay_my_buddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;

import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.service.IDepositService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

@WebMvcTest(controllers = WithdrawController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WithdrawControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IDepositService iDepositService;

  @MockBean
  private IUserService iUserService;

  User user;

  public void setup() {

    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("testuser@email.com");
    userAccount.setPassword("password");

    user = new User();
    user.setUserAccount(userAccount);

  }

  @Test
  public void getViewWithdrawPageModelTest() throws Exception {
    mockMvc.perform(get("/withdraw")).andExpect(status().isOk());
  }

  @Test
  public void withdrawTest() throws Exception {

    DepositDTO depositDTO = new DepositDTO();
    depositDTO.setAmount(120);
    depositDTO.setIban("FR332500000550");
    depositDTO.setCurrency("$");
    depositDTO.setDescription("first");
    when(iUserService.getConnectedUser()).thenReturn(user);

    mockMvc
        .perform(post("/withdraw").param("amount", "120").param("iban", "FR332500000550")
            .param("description", "first").param("currency", "$"))
        .andExpect(redirectedUrl("/profile")).andExpect(status().is3xxRedirection());
  }

}
