package com.paymybuddy.pay_my_buddy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;

/**
 * Interface for services operations concerning Transfert
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
public interface ITransfertService {

  /**
   * Retrieve a list of all Transferts
   *
   * @return List of Transferts
   */
  public List<Transfert> getTransferts();

  /**
   * Retrieve a page of all Transferts by a User
   *
   * @param user - User
   * @param page - int
   * @return Page of Transferts
   */
  public Page<Transfert> getTransfertsBySourceUser(User user, int page);

  /**
   * Retrieve a Transfert by id
   *
   * @param id - int
   * @return Transfert
   */
  public Transfert getTransfertById(int id);

  /**
   * Retrieve a page of all Transferts between two User
   *
   * @param user1 - User
   * @param user2 - User
   * @param page - int
   * @return Page of Transferts
   */
  public Page<Transfert> getTransfertsBetweenAnyUsers(User user1, User user2, int page);

  /**
   * Save a Transfert
   *
   * @param transfert - Transfert
   * @return Transfert
   */
  public Transfert saveTransfert(Transfert transfert);

  /**
   * Delete a Transfert by id
   *
   * @param id - int
   */
  public void deleteTransfertById(int id);

  /**
   * Delete a Transfert by User
   *
   * @param sourceUser - User
   */
  public void deleteTransfertBySourceUser(User sourceUser);

  /**
   * Create a transfert in Pay My Buddy
   *
   * @param connectedUser - User
   * @param transfertDto - TransferDTO
   * 
   * @return Transfert
   */
  public Transfert createTransfert(User connectedUser, TransferDTO transfertDto)
      throws UserBalanceException, UserAccountException;

}
