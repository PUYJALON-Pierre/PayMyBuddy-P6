package com.paymybuddy.pay_my_buddy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

  @Autowired
  IUserService iUserService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  AppAccountRepository accountRepository;

  private User user1;

  private User user2;

  @BeforeEach
  public void userInit() throws UserAccountException {

    // clear data
    userRepository.deleteAll();
    accountRepository.deleteAll();
    userAccountRepository.deleteAll();

    // create Users
    RegisterDTO registerDTO = new RegisterDTO();

    registerDTO.setFirstname("Yves");
    registerDTO.setLastname("Lelong");
    registerDTO.setBirthdate(new Date());
    registerDTO.setEmail("yveslelong@gmail.com");
    registerDTO.setPassword("yves");

    // when
    user1 = myUserDetailsService.registerUser(registerDTO);

    RegisterDTO registerDTO1 = new RegisterDTO();

    registerDTO1.setFirstname("Bruno");
    registerDTO1.setLastname("Lelong");
    registerDTO1.setBirthdate(new Date());
    registerDTO1.setEmail("brunolelong@gmail.com");
    registerDTO1.setPassword("bruno");

    // when
    user2 = myUserDetailsService.registerUser(registerDTO1);
  }
  
  @AfterAll
  public void clear() {
    
    // clear data
    userRepository.deleteAll();
    userAccountRepository.deleteAll();
    accountRepository.deleteAll();
    
  }

  @Test
  public void getUsersByPageTest() throws UserAccountException {

    // when

    // then
    assertEquals(iUserService.getUsers(0).getTotalElements(), 2);

    assertEquals(iUserService.getUsers(0).getContent().get(1).getFirstname(), "Yves");
    assertEquals(iUserService.getUsers(0).getContent().get(1).getLastname(), "Lelong");

    assertEquals(iUserService.getUsers(0).getContent().get(0).getFirstname(), "Bruno");
    assertEquals(iUserService.getUsers(0).getContent().get(0).getLastname(), "Lelong");

  }

  @Test
  public void getUserByIdTest() {

    assertEquals(iUserService.getUserById(user1.getUserID()).getFirstname(), "Yves");
    assertEquals(iUserService.getUserById(user1.getUserID()).getLastname(), "Lelong");

    assertEquals(iUserService.getUserById(user2.getUserID()).getFirstname(), "Bruno");
    assertEquals(iUserService.getUserById(user2.getUserID()).getLastname(), "Lelong");

  }

  @Test
  public void saveUserTest() throws UserAccountException {

    // Given

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);

    // Save AppAccount to generate id by jpa
    accountRepository.save(newAppAccount);

    UserAccount newAccount = new UserAccount();
    newAccount.setEmail("bertrandblanc@gmail.com");
    newAccount.setOnlineStatus(true);
    newAccount.setPassword("bertrand");
    // Save AppAccount to generate id by jpa
    userAccountRepository.save(newAccount);

    // Create new User
    User newUser = new User("Bertrand", "Blanc", new Date(), newAccount, newAppAccount);

    // when
    iUserService.saveUser(newUser);

    assertEquals(iUserService.getUsersList().size(), 3);

  }

  @Test
  public void updateUserTest() {
    // Retrieve user called Yves Lelong
    User updateUser = iUserService.getUserById(user1.getUserID());
    updateUser.setFirstname("Alfonse");
    updateUser.setLastname("Arnold");

    // when
    iUserService.updateUser(updateUser);

    assertEquals(iUserService.getUsers(0).getTotalElements(), 2);

    assertEquals(iUserService.getUserById(user1.getUserID()).getFirstname(), "Alfonse");
    assertEquals(iUserService.getUserById(user1.getUserID()).getLastname(), "Arnold");

  }

  @Test
  public void deleteUserByIdTest() {

    // when
    iUserService.deleteUserById(user1.getUserID());

    assertEquals(iUserService.getUsers(0).getTotalElements(), 1);
  }

  @Test
  public void addFriendToUserTest() throws FriendException {

    // given
    user1.setFriendList(new ArrayList<>());

    user2.setFriendList(new ArrayList<>());
    // when
    iUserService.addFriendToUser(user1, user2);

    assertEquals(user1.getFriendList().get(0).getFirstname(), "Bruno");

  }

  @Test
  public void addFriendToUserButAlreadyExistTest() throws FriendException {

    // given
    user1.setFriendList(new ArrayList<>());
    user2.setFriendList(new ArrayList<>());

    // when add same user two times
    iUserService.addFriendToUser(user1, user2);
    try {
      iUserService.addFriendToUser(user1, user2);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "You are already friend with this person");
    }
  }

  @Test
  public void deleteFriendToUserTest() throws FriendException {
    user1.setFriendList(new ArrayList<>());
    user2.setFriendList(new ArrayList<>());
    iUserService.addFriendToUser(user1, user2);

    // when
    iUserService.deleteFriend(user1, user2);

    assertEquals(user1.getFriendList().isEmpty(), true);
  }

  @Test
  public void deleteFriendButNoExistTest() throws FriendException {
    user1.setFriendList(new ArrayList<>());

    // when
    try {
      iUserService.deleteFriend(user1, user2);
    } catch (Exception e) {
      assertEquals(e.getMessage(), "You are not friend with this person");
    }

  }

  @Transactional @Test
  public void findAllFriendTest() throws FriendException {

    // given
    user1.setFriendList(new ArrayList<>());
    iUserService.addFriendToUser(user1, user2);
    User yves = iUserService.getUserById(user1.getUserID());

    // when
    List<User> yvesFriendlist = iUserService.findAllFriend(yves);

    assertEquals(1, yvesFriendlist.size());
    assertEquals("Bruno", yvesFriendlist.get(0).getFirstname());

  }

  @Transactional @Test
  public void findAllTransfertTest() {
    // given
    user1.setTransfertList(new ArrayList<>());
    User yves = iUserService.getUserById(user1.getUserID());
    List<Transfert> transfertlist = iUserService.findAllTransfert(yves);

    assertEquals(0, transfertlist.size());

  }

  @Transactional @Test
  public void findAllDepositTest() {

    user1.setDepositList(new ArrayList<>());
    User yves = iUserService.getUserById(user1.getUserID());
    List<Deposit> depositList = iUserService.findAllDeposit(yves);

    assertEquals(0, depositList.size());

  }

  @Transactional
  @Test
  public void getUserByAppAccountTest() throws UserAccountException {

    assertEquals(user1,  iUserService.getUserByAppAcount(user1.getUserAccount()));
    assertNotEquals(user2,  iUserService.getUserByAppAcount(user1.getUserAccount()));

  }
  

  
}
