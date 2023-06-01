package com.paymybuddy.pay_my_buddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;

public interface IDepositService {

  
  public List <Deposit> getDeposits();
  
  public Page<Deposit> getDepositBySourceUser(User sourceUser, int page);
  
  public Optional<Deposit> getDepositById(int id);
  
  public void deleteDepositById(int id);
  
  public void deleteDepositBySourceUser(User user);
 
  public Deposit saveDeposit(User connectedUser, DepositDTO deposit);
 
  public Deposit saveWithdraw(User connectedUser, DepositDTO deposit)throws UserBalanceException;
}
