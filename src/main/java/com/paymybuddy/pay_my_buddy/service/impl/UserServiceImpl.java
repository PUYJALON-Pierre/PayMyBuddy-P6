package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

  UserRepository userRepository;

  UserAccountRepository userAccountRepository;

  public UserServiceImpl(UserRepository userRepository,
      UserAccountRepository userAccountRepository) {
    super();
    this.userRepository = userRepository;
    this.userAccountRepository = userAccountRepository;
  }

  @Override
  public Page<User> getUsers(int page) {

    return userRepository.findAll(PageRequest.of(page, 3));
  }

  @Override
  public User saveUser(User user) {

    // checking if email already exist
    if (userAccountRepository.findByEmail(user.getUserAccount().getEmail()) == null) {

      userRepository.save(user);
      return user;
    }

    return null;
  }

  @Override
  public User updateUser(User user) {

    // checking if user already exist before update
    Optional<User> userToUpdate = userRepository.findById(user.getUserID());
    if (userToUpdate != null) {

      userToUpdate.get().setUserID(user.getUserID());
      userToUpdate.get().setFirstname(user.getFirstname());
      userToUpdate.get().setLastname(user.getLastname());
      userToUpdate.get().setBirthdate(user.getBirthdate());
      userToUpdate.get().setFriendList(user.getFriendList());
      userToUpdate.get().setDepositList(user.getDepositList());
      userToUpdate.get().setTransfertList(user.getTransfertList());

      userRepository.save(userToUpdate.get());

      return userToUpdate.get();
    }
    ;

    return null;
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

}
