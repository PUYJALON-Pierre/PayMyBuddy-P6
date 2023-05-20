package com.paymybuddy.pay_my_buddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserAccountServiceTest {

  @Autowired
  IUserAccountService iUserAccountService;
  
  @Autowired
  UserAccountRepository userAccountRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  
  
  @Test
  public void createUserAccount () throws UserAccountException {
    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("harrybrown@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("harry"));
    
    iUserAccountService.createUserAccount(newUserAccount);
    
    assertEquals(iUserAccountService.findUserByEmail("harrybrown@gmail.com").getEmail(),"harrybrown@gmail.com");
  }
  
  
  @Test
  public void createUserAccountButEmailAlreadyExist () throws UserAccountException {
 
    try {
    UserAccount newUserAccount = new UserAccount();
    newUserAccount.setEmail("harrybrown@gmail.com");
    newUserAccount.setPassword(passwordEncoder.encode("harry"));
    
    iUserAccountService.createUserAccount(newUserAccount);}
    
    catch(Exception e){
    
      assertEquals(e.getMessage(), "An account has already registred with this Email");
      }

  }
  
  
  @Test
  public void updateUserAccount () throws UserAccountException {
    String oldPassword = iUserAccountService.findUserByEmail("patrickarnold@gmail.com").getPassword();
 
    
    UserAccount userAccountToUpdate = iUserAccountService.findUserByEmail("patrickarnold@gmail.com");
    userAccountToUpdate.setPassword(passwordEncoder.encode("hermione"));
    
    iUserAccountService.updateUserAccount(userAccountToUpdate);
    
 assertNotEquals(iUserAccountService.findUserByEmail("patrickarnold@gmail.com").getPassword(), oldPassword);
   
  }
  
  
  @Test
  public void updateUserAccountWithBadInfos () throws UserAccountException {
  
    try {
    UserAccount userAccountToUpdate = new UserAccount();
        userAccountToUpdate.setEmail("bademail@gmail.com");
    
    iUserAccountService.updateUserAccount(userAccountToUpdate);}
    
    catch (Exception e) {
     
 assertEquals(e.getMessage(), "User Account to update not founded");}
  }
  
  
  @Transactional
  @Test
  public void deleteUserAccount () {
String emailToDelete = "harrybrown@gmail.com";
   
    
    iUserAccountService.deleteUserAccountByEmail(emailToDelete);
    
 assertThat((userAccountRepository.findByEmail(emailToDelete)).isEmpty());
   
  }
  
  @Test
  public void findByUserEmail () {
String emailToFind = "jeanbolt@gmail.com";
   
    
    iUserAccountService.findUserByEmail(emailToFind);
    
assertEquals(  iUserAccountService.findUserByEmail(emailToFind).getEmail(), "jeanbolt@gmail.com");
   
  }
  
}
