package com.paymybuddy.pay_my_buddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;

import com.paymybuddy.pay_my_buddy.service.ITransfertService;

import com.paymybuddy.pay_my_buddy.service.IUserService;

@WebMvcTest(controllers = TransfertController.class) @AutoConfigureMockMvc(addFilters = false)
public class TransfertControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUserService iUserService;

  @MockBean
  private ITransfertService iTransfertService;

  @Mock
  Page<Transfert> transfertPage;

  User user;
  User friend;

  @BeforeEach
  public void setup() {

 
    
    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("testuser@email.com");
    userAccount.setPassword("password");

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(1000);

    user = new User("Bertrand", "Blanc", new Date(), userAccount, newAppAccount);

    UserAccount userAccount1 = new UserAccount();
    userAccount1.setEmail("testuser1@email.com");
    userAccount1.setPassword("password");

    friend = new User();
    friend.setUserAccount(userAccount);

  }

  @Test
  public void getViewTransferPageModelTest() throws Exception {

    
    when(iUserService.getConnectedUser()).thenReturn(user);
    when(iTransfertService.getTransfertsBetweenAnyUsers(user,
        user, 0)).thenReturn(transfertPage);
 
    
    mockMvc.perform(get("/transfer")).andExpect(status().isOk());
    
    
    
  }

  @Test
  public void makeTransfertTest() throws Exception {
    
    when(iUserService.getConnectedUser()).thenReturn(user);

TransferDTO transfer = new TransferDTO();
transfer.setAmount(10);
transfer.setCurrency("$");
transfer.setDescription("1");
transfer.setRecipient("testuser@email.com");

Transfert transferDone = new Transfert();


when(iTransfertService.createTransfert(user, transfer)).thenReturn(transferDone);
  
    mockMvc.perform(post("/transfer").param("amount", "10").param("currency", "$").param("description", "1").param("recipient", "testuser@email.com")).andExpect(redirectedUrl("/transfer")).andExpect(status().is3xxRedirection());
  }

}
