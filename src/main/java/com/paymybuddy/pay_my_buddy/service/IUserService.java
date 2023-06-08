package com.paymybuddy.pay_my_buddy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;

/**
 * Interface for services operations concerning User
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
public interface IUserService {

  /**
   * Retrieve a list of all User
   *
   * @return List of User
   */
  public List<User> getUsersList();

  /**
   * Retrieve a page of all Users
   *
   * @param page - int
   * @return Page of User
   */
  public Page<User> getUsers(int page);

  /**
   * Save a User
   *
   * @param user - User
   * @return User
   */
  public User saveUser(User user);

  /**
   * Update a User
   *
   * @param user - User
   * @return User
   */
  public User updateUser(User user);

  /**
   * Delete a User by id
   *
   * @param id - int
   */
  public void deleteUserById(int id);

  /**
   * Add a Friend to a specific User
   *
   * @param user - User
   * @param friend - User
   * @return User
   */
  public User addFriendToUser(User user, User friend) throws FriendException;

  /**
   * Delete a Friend from a specific User
   *
   * @param user - User
   * @param friend - User
   * @return User
   */
  public User deleteFriend(User user, User friend) throws FriendException;

  /**
   * Retrieve a list of all friends from a User
   *
   * @param user - User
   * @return List of User
   */
  public List<User> findAllFriend(User user);

  /**
   * Retrieve a list of all transferts from a User
   *
   * @param user - User
   * @return List of Transfert
   */
  public List<Transfert> findAllTransfert(User user);

  /**
   * Retrieve a list of all deposits from a User
   *
   * @param user - User
   * @return List of Deposit
   */
  public List<Deposit> findAllDeposit(User user);

  /**
   * Find a User by his id
   *
   * @param id - int
   * @return User
   */
  public User getUserById(int id);

  /**
   * Find User connected
   *
   * @return User
   */
  public User getConnectedUser() throws UserAccountException;

  /**
   * Find a User by his userAccount
   *
   * @param userAccount - UserAccount
   * @return User
   */
  public User getUserByAppAcount(UserAccount userAccount) throws UserAccountException;

  
  
  public Page<User>getPotentialFriends(List<Integer> friendListID, Pageable page);
  
  
  public Page<User>searchUsersByEmail(String email, List<Integer> friendListID, Pageable page);
  
}
