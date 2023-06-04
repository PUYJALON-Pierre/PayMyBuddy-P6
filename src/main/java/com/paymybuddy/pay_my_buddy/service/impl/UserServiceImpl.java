package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IUserService;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

@Service
public class UserServiceImpl implements IUserService {

  UserRepository userRepository;

  UserAccountRepository userAccountRepository;

  public UserServiceImpl(UserRepository userRepository, UserAccountRepository userAccountRepository,
      MyUserDetailsService myUserDetailsService) {
    super();
    this.userRepository = userRepository;
    this.userAccountRepository = userAccountRepository;

  }

  @Override
  public Page<User> getUsers(int page) {

    return userRepository.findAll(PageRequest.of(page, 5));
  }

  @Override
  public User saveUser(User user) {

    return userRepository.save(user);
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
  public User addFriendToUser(User user, User friend) throws FriendException {

    // checking if user is already a friend
    if (user.getFriendList().contains(friend)) {
      throw new FriendException("You are already friend with this person");
    } else {

      List<User> updateFriendList = user.getFriendList();
      updateFriendList.add(friend);
      user.setFriendList(updateFriendList);
      userRepository.save(user);
      return user;
    }

  }

  @Override
  public User deleteFriend(User user, User friend) throws FriendException {

    if (user.getFriendList().contains(friend)) {

      List<User> updateFriendList = user.getFriendList();
      updateFriendList.remove(friend);
      user.setFriendList(updateFriendList);
      userRepository.save(user);
    } else {
      throw new FriendException("You are not friend with this person");
    }
    ;
    return user;
  }

  @Override
  public List<User> findAllFriend(User user) {
    List<User> friendList = user.getFriendList();

    return friendList;
  }

  @Override
  public List<Transfert> findAllTransfert(User user) {

    List<Transfert> transfertList = user.getTransfertList();
    return transfertList;
  }

  @Override
  public List<Deposit> findAllDeposit(User user) {
    List<Deposit> depositList = user.getDepositList();
    return depositList;
  }

  @Override
  public User getUserById(int id) {
    return userRepository.findById(id).get();
  }

  @Override
  public User getConnectedUser() throws UserAccountException {

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    if (userAccountRepository.findByEmail(username).isEmpty()) {
      throw new UserAccountException("Email :" + username + ", does not match any user.");
    }
    UserAccount userAccountConnected = userAccountRepository.findByEmail(username).get();
    return userRepository.findByUserAccount(userAccountConnected).get();

  }

  @Override
  public List<User> getUsersList() {

    return userRepository.findAll();
  }

  @Override
  public User getUserByAppAcount(UserAccount userAccount) throws UserAccountException {

    if (userRepository.findByUserAccount(userAccount).isEmpty()) {

      throw new UserAccountException("This user account, does not match any user.");

    } else {

      return userRepository.findByUserAccount(userAccount).get();
    }
  }

}
