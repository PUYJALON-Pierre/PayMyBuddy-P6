package com.paymybuddy.pay_my_buddy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.DTO.UpdateDTO;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.service.IUserService;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

@WebMvcTest(controllers = UpdateController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UpdateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
 private IUserService iUserService;
  
  @MockBean
  private PasswordEncoder passwordEncoder;
  
  
  User user;

  @BeforeEach
  public void setup() {

    UserAccount userAccount = new UserAccount();
    userAccount.setEmail("testuser@email.com");
    userAccount.setPassword("password");

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);

    user = new User("Bertrand", "Blanc", new Date(), userAccount, newAppAccount);
  }
  
  @Test
  public void getViewUpdatePageModelTest() throws Exception {
    mockMvc.perform(get("/update")).andExpect(status().isOk());
  }

  @Test
  public void updateUserConnectedTest() throws Exception {

    UpdateDTO update = new UpdateDTO();
    update.setBirthdate(new Date());
    update.setFirstname("test");
    update.setLastname("test");
    update.setPassword("test");
    
    user.setFirstname(update.getFirstname());
    user.setLastname(update.getLastname());
    user.setBirthdate(update.getBirthdate());
    user.getUserAccount().setPassword(passwordEncoder.encode(update.getPassword()));
    
    when(iUserService.getConnectedUser()).thenReturn(user);
    when( iUserService.saveUser(user)).thenReturn(user);

    mockMvc
        .perform(post("/update").param("birthdate", "2011-01-01").param("firstname", "test").param("lastname", "test")
            .param("password", "test"))
        .andExpect(redirectedUrl("/profile")).andExpect(status().is3xxRedirection());
  }

}
