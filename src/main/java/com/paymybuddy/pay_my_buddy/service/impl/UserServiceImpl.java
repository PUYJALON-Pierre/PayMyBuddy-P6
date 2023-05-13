package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IUserService;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

@Service
public class UserServiceImpl implements IUserService {

  MyUserDetailsService myUserDetailsService;
  
  UserRepository userRepository;

  UserAccountRepository userAccountRepository;

  public UserServiceImpl(UserRepository userRepository,
      UserAccountRepository userAccountRepository, MyUserDetailsService myUserDetailsService) {
    super();
    this.userRepository = userRepository;
    this.userAccountRepository = userAccountRepository;
    this.myUserDetailsService = myUserDetailsService;
  
  }

  
  
  @Override
  public Page<User> getUsers(int page) {

    return userRepository.findAll(PageRequest.of(page, 3));
  }

  @Override
  public User saveUser(RegisterDTO registerDTO ) throws UserAccountException {

    // checking if email already exist
    if ( userAccountRepository.findByEmail(registerDTO.getEmail()) != null) {
      
     throw new UserAccountException("An account has already registred with this Email");  
    }
    
    return myUserDetailsService.saveUser(registerDTO);
  }
  
  

  @Override
  public User updateUser(User user) {


      return userRepository.save(user);
    

   
  }

  @Override
  public void deleteUserById(int id) {

    userRepository.deleteById(id);

  }

  @Override
  public User addFriendToUser(User user, User friend) {

    // checking if user is already a friend

    if (user.getFriendList().contains(friend)) {
      System.out.println("already friend");
      return null;
    } else {
      user.getFriendList().add(friend);
      return friend;
    }

  }

  @Override
  public User deleteFriend(User user, User friend) {

    if (user.getFriendList().contains(friend)) {
      user.getFriendList().remove(friend);
    }
    return friend;
  }

  @Override
  public List<User> findAllFriend(int id) {
    Optional<User> user = userRepository.findById(id);
    return user.get().getFriendList();
  }

  @Override
  public List<Transfert> findAllTransfert(User user) {
    return user.getTransfertList();
  }

  @Override
  public List<Deposit> findAllDeposit(User user) {
    return user.getDepositList();
  }

  @Override
  public User getUserById(int id) {
    return userRepository.findById(id).get();
  }

}
