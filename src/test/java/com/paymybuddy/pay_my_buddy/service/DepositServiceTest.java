package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.DepositRepository;

@SpringBootTest
public class DepositServiceTest {

  @Autowired
  IDepositService iDepositService;
  
  @Autowired
  DepositRepository depositRepository;
  
  @Mock
  User user;
  
  @Test
  public void getDeposits() {
    
    //when
    iDepositService.getDeposits();
    
    assertEquals( iDepositService.getDeposits().size(), 4);
    assertEquals( iDepositService.getDeposits().get(0).getAmount(), 120);
    assertEquals( iDepositService.getDeposits().get(0).getBankAccountIBAN(), "FR332500000550");
  }
  
  
  
  
  //besoin de créer user pb...
  @Disabled
  @Test
  public void getDepositsBySourceUser() {
    
    //when
    iDepositService.getDeposits();
    
    assertEquals( iDepositService.getDeposits().size(), 4);
    
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
  
  
  //user cant be nullpb...
@Disabled
  @Test
  public void saveDeposit() {

Deposit newDeposit = new Deposit();
newDeposit.setSourceUser(user);
newDeposit.setBankAccountIBAN("FR45215475646");
newDeposit.setAmount(150);
//newDeposit.setDateTime(new GregorianCalendar());
newDeposit.setCurrency("$");
newDeposit.setDescription("deposit");
newDeposit.setFee(0.005);


    //when
iDepositService.saveDeposit(newDeposit);
    
assertEquals(iDepositService.getDeposits().size(), 5);

  }
  
  @Test
  public void deleteDepositById() {
    
    int depositId = 1;
 
    //when
iDepositService.deleteDepositById(depositId);
assertEquals(iDepositService.getDeposits().size(), 4);
  }
  
  //pb car user mock?? que un???
  @Disabled
  @Test
  public void deleteDepositBySourceUser() {
    
    int depositId = 1;
 
    //when
iDepositService.deleteDepositById(depositId);

  }
  
}
