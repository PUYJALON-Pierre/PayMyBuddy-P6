package com.paymybuddy.pay_my_buddy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;

public interface IUserService {

  
  public Page<User> getUsers(int page);
  
  public User saveUser(RegisterDTO registerDTO) throws UserAccountException;
  
  public User updateUser(User user);
  
  public void deleteUserById(int id);
  
  public User addFriendToUser(User user, User friend);
  
  public User deleteFriend(User user, User friend);
 
  public List<User> findAllFriend(int id);
  
  public List<Transfert> findAllTransfert(User user);
  
  public List<Deposit> findAllDeposit(User user);
  
  public User getUserById(int id);
  
  
  
}
