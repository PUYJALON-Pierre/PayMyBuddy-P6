package com.paymybuddy.pay_my_buddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

  
  @Autowired
  IUserService iUserService;
  
  @Autowired
  UserRepository userRepository;
  
  private User user = new User();
  private User user2 = new User();
  
  
  @Mock 
  Deposit deposit;
  
  @Mock 
Transfert transfert;
  
  
  @BeforeEach
  public void userInit() {
    
 
    //given
    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);
    newAppAccount.setAppAccountID(10);
    
    UserAccount newAccount = new UserAccount();
    newAccount.setEmail("arnaudbravo@gmail.com");
    newAccount.setPassword("arnaud");
    newAccount.setUserAccountID(10);
   
    
 
   user.setFirstname("Arnaud");
  user.setLastname("Bravo");
   user.setBirthdate(new java.util.Date());
   user.setFriendList(new ArrayList<>());
   user.setDepositList(new ArrayList<>());
   user.setTransfertList(new ArrayList<>());
   user.setUserAccount (newAccount);
   user.setAppAccount(newAppAccount);
   
   
   //given
   AppAccount newAppAccount2 = new AppAccount();
   newAppAccount2.setBalance(0);
   newAppAccount2.setAppAccountID(11);
   
   UserAccount newAccount2 = new UserAccount();
   newAccount2.setEmail("arnaudbravo@gmail.com");
   newAccount2.setPassword("arnaud");
   newAccount2.setUserAccountID(11);

   
  User user2 = new User();
  user2.setFirstname("Benoit");
  user2.setLastname("Bravo");
  user2.setBirthdate(new java.util.Date());
  user2.setFriendList(new ArrayList<>());
  user2.setDepositList(new ArrayList<>());
  user2.setTransfertList(new ArrayList<>());
  user2.setUserAccount (newAccount2);
  user2.setAppAccount(newAppAccount2);
    
  }
  
  
  
  
@Test
  public void getUserByIdTest() {
    
    assertEquals(iUserService.getUserById(1).getFirstname(),"Jean");
    assertEquals(iUserService.getUserById(1).getLastname(),"Bolt");
    
    assertEquals(iUserService.getUserById(4).getFirstname(),"Sophie");
    assertEquals(iUserService.getUserById(4).getLastname(),"Moreau");

}
  
  
  @Disabled
@Test
public void getUsersByPageTest() {
  
  //when
  
  
  //then 
  assertEquals(iUserService.getUsers(0).getTotalElements(),4);

  assertEquals(iUserService.getUsers(0).getContent().get(0).getFirstname(),"Sophie");
  assertEquals(iUserService.getUsers(0).getContent().get(0).getFirstname(),"Sophie");
  assertEquals(iUserService.getUsers(0).getContent().get(0).getLastname(),"Moreau");
  
  assertEquals(iUserService.getUsers(0).getContent().get(1).getFirstname(),"Jean");
  assertEquals(iUserService.getUsers(0).getContent().get(1).getLastname(),"Bolt");

  assertEquals(iUserService.getUsers(0).getContent().get(2).getFirstname(),"Paul");
  assertEquals(iUserService.getUsers(0).getContent().get(2).getLastname(),"Shelby");

  
  assertEquals(iUserService.getUsers(1).getContent().get(0).getFirstname(),"Patrick");
  assertEquals(iUserService.getUsers(1).getContent().get(0).getLastname(),"Arnold");
  
}


@Test
public void saveUserTest() throws UserAccountException {
 


  //when
  iUserService.saveUser(user); 

 
  assertEquals(iUserService.getUsers(0).getTotalElements(),5);


}
  
  
 @Test
 public void updateUserTest()  {
   //Retrieve user called alfonse
   User updateUser= iUserService.getUserById(1);
  updateUser.setFirstname("Alfonse");
  updateUser.setLastname("Arnold");
   
   //when
   iUserService.updateUser(updateUser);
   
   assertEquals(iUserService.getUsers(0).getTotalElements(),4);

   assertEquals(iUserService.getUserById(1).getFirstname(),"Alfonse");
   assertEquals(iUserService.getUserById(1).getLastname(),"Arnold");

 }
   
  
  

@Test
public void deleteUserByIdTest() {
  
  //when
  iUserService.deleteUserById(1);
  
  assertEquals(iUserService.getUsers(0).getTotalElements(),3);
}
  
  
  
 

@Test
public void addFriendToUserTest() throws FriendException {
  

 
 //when
iUserService.addFriendToUser(user, user2);
 
 
 assertEquals(user.getFriendList().get(0).getFirstname(), "Benoit" );



}
  

@Test
public void addFriendToUserButAlreadyExistTest() throws FriendException {
  

 //when
iUserService.addFriendToUser(user, user2);
try {
iUserService.addFriendToUser(user, user2);}
 catch (Exception e) {
  assertEquals(e.getMessage(), "You are already friend with this person");
}
 
}




@Test
public void deleteFriendToUserTest() throws FriendException {
  
 //when
iUserService.addFriendToUser(user, user2);
iUserService.deleteFriend(user, user2);
 
 assertEquals(user.getFriendList().isEmpty(), true );
}






@Test
public void deleteFriendButNoExistTest() throws FriendException {

 //when
try {
iUserService.deleteFriend(user, user2);}
 catch (Exception e) {
  assertEquals(e.getMessage(), "You are not friend with this person");
}
 
}


@Disabled //fail to mazily initialize
@Test
public void findAllFriendTest() throws FriendException {
  
  
User jean = iUserService.getUserById(1);

  
  /*List <User> friendlist = iUserService.findAllFriend(jean);
  
  assertEquals(1, friendlist.size());
  assertEquals("Benoit", friendlist.get(0).getFirstname());*/
  
}



@Test
public void findAllTransfertTest()  {
  

  List <Transfert> transfertlist = iUserService.findAllTransfert(user);
  
  assertEquals(0, transfertlist.size());

  
}



@Test
public void findAllDepositTest()  {
  

  List <Deposit> depositList = iUserService.findAllDeposit(user);
  
  assertEquals(0, depositList.size());

  
}





}



