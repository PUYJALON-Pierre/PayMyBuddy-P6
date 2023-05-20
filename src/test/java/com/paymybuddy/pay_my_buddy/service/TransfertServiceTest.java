package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.TransfertRepository;

@SpringBootTest
public class TransfertServiceTest {

  
  @Autowired
  ITransfertService iTransfertService;
  
  @Autowired
  TransfertRepository transfertRepository;
  
  @Mock
  User user;
  
  
  
  
  
  
  
  @Test
  public void getTransferts() {
    
    //when
    iTransfertService.getTransferts();
    
    assertEquals( iTransfertService.getTransferts().size(), 5);
    assertEquals( iTransfertService.getTransferts().get(0).getAmount(), 10);
    assertEquals( iTransfertService.getTransferts().get(0).getDescription(), "movie");
  }
  
//besoin de créer user pb...
  @Disabled
  @Test
  public void getTransfertBySourceUser() {
    
    //when
   
    
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
  
  
  
  
  //besoin de créer user pb...
  @Disabled
  @Test
  public void getTransfertBetweenUser() {
    
    //when
   
    
  }
  
  
  //besoin de créer user pb...
  @Disabled
  @Test
  public void saveTransfert() {
    
    //when
   
    
  }
  
  
  //besoin de créer user pb...
  @Disabled
  @Test
  public void deleteTransfertById() {
    
    //when
   
    
  }
  
  
  //besoin de créer user pb...
  @Disabled
  @Test
  public void deleteTransfertBySourceUser() {
    
    //when
   
    
  }
  
}
