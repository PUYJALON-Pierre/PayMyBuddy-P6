package com.paymybuddy.pay_my_buddy.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;

public interface ITransfertService {

  
  public List<Transfert> getTransferts();
  
  public Page <Transfert> getTransfertsBySourceUser(User user, int page);
  
  public Transfert getTransfertById(int id);
  
  public Page <Transfert> getTransfertsBetweenAnyUsers(User user1, User user2, int page);
  
  public Transfert saveTransfert(Transfert transfert);
  
  public void deleteTransfertById(int id);
  
  public void deleteTransfertBySourceUser(User sourceUser);
  
 public Transfert createTransfert(User connectedUser, TransferDTO transfertDto)throws UserBalanceException, UserAccountException;
 
  

}
