package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.TransfertRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@SpringBootTest @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransfertServiceTest {

  @Autowired
IUserService iUserService;
  
  @Autowired
  ITransfertService iTransfertService;
  
  @Autowired
  TransfertRepository transfertRepository;
  

  
  @Test
  public void getTransferts() {
    
    //when
    iTransfertService.getTransferts();
    
    assertEquals( iTransfertService.getTransferts().size(), 4);
    assertEquals( iTransfertService.getTransferts().get(0).getAmount(), 10);
    assertEquals( iTransfertService.getTransferts().get(0).getDescription(), "movie");
  }
  

  @Test
  public void getTransfertBySourceUser() {
    
 
   User user = iUserService.getUserById(1);
    
    //when
    Page<Transfert> transfer= iTransfertService.getTransfertsBySourceUser(user, 0);
   

    assertEquals(transfer.getContent().get(0).getAmount(), 10);
    assertEquals(transfer.getNumberOfElements(), 1);
    
  }
  
  
  
  
  
  @Test
  public void getTransfertById() {
    
    int transfert1 = 1;
    int transfert2 = 2;
   
    //when

    assertEquals( iTransfertService.getTransfertById(transfert1).getAmount(), 10);
    assertEquals(iTransfertService.getTransfertById(transfert1).getDescription(), "movie");
    assertEquals( iTransfertService.getTransfertById(transfert2).getAmount(), 10);
    assertEquals(iTransfertService.getTransfertById(transfert2).getDescription(), "food");
  }
  
  
  
  

  @Test
  public void getTransfertBetweenUser() {
    User user1 = iUserService.getUserById(1);
    
    User user2 = iUserService.getUserById(1);
   
    //when
    Page<Transfert> transfer = iTransfertService.getTransfertsBetweenAnyUsers(user1, user2, 0);
    
    assertEquals(transfer.getContent().get(0).getAmount(), 10);
    assertEquals(transfer.getContent().get(0).getDescription(), "movie");
    assertEquals(transfer.getContent().get(1).getAmount(), 10);
    assertEquals(transfer.getContent().get(1).getDescription(), "food");
    assertEquals(transfer.getNumberOfElements(), 2);
    
  }
  
  
  @Test
  public void saveTransfert() throws UserBalanceException, UserAccountException {
    
    User user1 = iUserService.getUserById(1);

    
TransferDTO newTransfer = new TransferDTO();

newTransfer.setRecipient("sophiemoreau@gmail.com");
newTransfer.setAmount(15);
newTransfer.setCurrency("$");
newTransfer.setDescription("deposit");



    //when
iTransfertService.createTransfert(user1, newTransfer);
    
assertEquals(iTransfertService.getTransferts().size(), 5);
    
  }
  
  
  
  @Test
  public void saveTransfertFailUsername() throws UserBalanceException, UserAccountException {
    
    User user1 = iUserService.getUserById(1);

    
TransferDTO newTransfer = new TransferDTO();

newTransfer.setRecipient("sophie@gmail.com");
newTransfer.setAmount(15);
newTransfer.setCurrency("$");
newTransfer.setDescription("deposit");

try {
  //when
iTransfertService.createTransfert(user1, newTransfer);}
 catch (Exception e) {
  assertEquals(e.getMessage(), "Recipient of this transfer cannot be find");
}
    
  }
  
  
  @Test
  public void saveTransfertFailBalance() throws UserBalanceException, UserAccountException {
    
    User user1 = iUserService.getUserById(1);

    
TransferDTO newTransfer = new TransferDTO();

newTransfer.setRecipient("sophiemoreau@gmail.com");
newTransfer.setAmount(150);
newTransfer.setCurrency("$");
newTransfer.setDescription("deposit");


try {
  //when
iTransfertService.createTransfert(user1, newTransfer);}
 catch (Exception e) {
  assertEquals(e.getMessage(), "You dont have enough money on your app account to make this transfer");
}
    
  }
  
  

  @Test
  public void deleteTransfertById() {
    
    int transferId = 1;
    
    //when
iTransfertService.deleteTransfertById(transferId);
assertEquals(iTransfertService.getTransferts().size(), 3);
  }
    

  

  @Test
  public void deleteTransfertBySourceUser() {
   
    User user1 = iUserService.getUserById(1);
    
    //when
    iTransfertService.deleteTransfertBySourceUser(user1);
    assertEquals(iTransfertService.getTransferts().size(), 3);
    
  }
  
}
