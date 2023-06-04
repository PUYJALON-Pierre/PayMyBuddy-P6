package com.paymybuddy.pay_my_buddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;

/**
 * Interface for services operations concerning Deposit
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
public interface IDepositService {

  /**
   * Retrieve a list of all deposits
   *
   * @return List of Deposit
   */
  public List<Deposit> getDeposits();

  /**
   * Retrieve a page of all deposits by a User
   *
   * @param sourceUser - User
   * @param page - int
   * @return Page of Deposit
   */
  public Page<Deposit> getDepositBySourceUser(User sourceUser, int page);

  /**
   * Retrieve a deposit by id
   *
   * @param id - int
   * @return Deposit
   */
  public Optional<Deposit> getDepositById(int id);

  /**
   * Delete a Deposit by id
   *
   * @param id - int
   */
  public void deleteDepositById(int id);

  /**
   * Save a deposit
   *
   * @param connectedUser - User
   * @param deposit - DepositDTO
   * @return Deposit
   */
  public Deposit saveDeposit(User connectedUser, DepositDTO deposit);

  /**
   * Save a deposit
   *
   * @param connectedUser - User
   * @param deposit - DepositDTO
   * @return Deposit
   */
  public Deposit saveWithdraw(User connectedUser, DepositDTO deposit) throws UserBalanceException;
}
