package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.DepositRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@SpringBootTest @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DepositServiceTest {

  @Autowired
  IDepositService iDepositService;
  
  @Autowired
  DepositRepository depositRepository;
  
  @Autowired
  UserRepository userRepository;
  
  
  @Test
  public void getDeposits() {
    
    //when
    iDepositService.getDeposits();
    
    assertEquals( iDepositService.getDeposits().size(), 5);
    assertEquals( iDepositService.getDeposits().get(0).getAmount(), 120);
    assertEquals( iDepositService.getDeposits().get(0).getBankAccountIBAN(), "FR332500000550");
  }
  
  
 

  @Test
  public void getDepositsBySourceUser() {
    
    
    User user = userRepository.findById(1).get();
    
    //when
    Page<Deposit> deposit= iDepositService.getDepositBySourceUser(user,0);
   

    assertEquals( deposit.getContent().get(0).getAmount(), 120);
    assertEquals( deposit.getNumberOfElements(), 1);
  }
  
  
  @Test
  public void getDepositById() {
    
    int deposit1 = 1;
    int deposit2 = 2;
   
    //when
    assertEquals( iDepositService.getDepositById(deposit1).get().getAmount(), 120);
    assertEquals( iDepositService.getDepositById(deposit1).get().getBankAccountIBAN(), "FR332500000550");
    assertEquals( iDepositService.getDepositById(deposit2).get().getAmount(), 50);
    assertEquals( iDepositService.getDepositById(deposit2).get().getBankAccountIBAN(), "BE784525468541");
  }
  
 

  @Test
  public void saveDeposit() {

    User user = userRepository.findById(1).get();
    
    
DepositDTO newDeposit = new DepositDTO();

newDeposit.setIban("FR45215475646");
newDeposit.setAmount(150);

newDeposit.setCurrency("$");
newDeposit.setDescription("deposit");



    //when
iDepositService.saveDeposit(user, newDeposit );
    
assertEquals(iDepositService.getDeposits().size(), 5);

  }
  
  @Test
  public void deleteDepositById() {
    
    int depositId = 1;
 
    //when
iDepositService.deleteDepositById(depositId);
assertEquals(iDepositService.getDeposits().size(), 4);
  }
  
  
  
}
