package com.paymybuddy.pay_my_buddy.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.TransfertRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.ITransfertService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.transaction.Transactional;

@Service
public class TransfertServiceImpl implements ITransfertService {

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  UserAccountRepository userAccountRepository;
  
  TransfertRepository transfertRepository;

  public TransfertServiceImpl(TransfertRepository transfertRepository) {
    super();
    this.transfertRepository = transfertRepository;
  }

  @Override
  public List<Transfert> getTransferts() {

    return transfertRepository.findAll();
  }

  @Override
  public Page<Transfert> getTransfertsBySourceUser(User user, int page) {

    return transfertRepository.findBySourceUser(user, PageRequest.of(page, 3));
  }

  @Override
  public Transfert getTransfertById(int id) {

    return transfertRepository.findById(id).get();
  }

  @Override
  public Page<Transfert> getTransfertsBetweenAnyUsers(User user1, User user2, int page) {

    return transfertRepository.findBySourceUserOrRecipient(user1, user2,  PageRequest.of(page, 3));
  }

  @Override
  public Transfert saveTransfert(Transfert transfert) {

    return transfertRepository.save(transfert);
  }

  @Override
  public void deleteTransfertById(int id) {
    transfertRepository.deleteById(id);

  }

  @Override
  public void deleteTransfertBySourceUser(User sourceUser) {
    transfertRepository.deleteBySourceUser(sourceUser);

  }
//transactionnal?
  @Override
  public Transfert createTransfert(User connectedUser, TransferDTO transferDto)
      throws UserBalanceException, UserAccountException {

    double amount = transferDto.getAmount();
    //checking amount is not negative before transfert
    if (transferDto.getAmount()<=0) { throw new UserBalanceException("You can't make a transfert with 0 or negative amount");

    }
    

    //checking that recipient not null and exist
 
    if (userAccountRepository.findByEmail(transferDto.getRecipient()).isEmpty()) {   
      
      throw new UserAccountException("Recipient of this transfer cannot be find");
    }

      //retrieving user from email
      User recipientUser= userRepository.findByUserAccount(userAccountRepository.findByEmail(transferDto.getRecipient()).get()).get();
      
    
    
    //checking if userbalance okay for transfer
    double amountWithFee = amount + (amount*0.005);
    
    if (amountWithFee>connectedUser.getAppAccount().getBalance()) {
      
      throw new UserBalanceException("You dont have enough money on your app account to make this transfer");
      
    }
    

    //substract on user balance
    if(connectedUser.getAppAccount().getBalance()>=amountWithFee) {
    
      
     AppAccount updateAppAccount =  connectedUser.getAppAccount();
     double result = updateAppAccount.getBalance()-(amountWithFee);
     double arrondi =Math.round(result*100.0)/100.0;
     updateAppAccount.setBalance(arrondi);
      
     connectedUser.setAppAccount(updateAppAccount);

     
     //add credit to new user balance
      recipientUser.getAppAccount().setBalance(recipientUser.getAppAccount().getBalance()+amount);

    }
   //besoin de save les 2 user pour garder la balance?
    //setting date and usersource before saving transfert
     userRepository.save(recipientUser);
     userRepository.save(connectedUser);
     
     
    //créer transfert à sauvegarder
    Transfert newTransfert = new Transfert();
    
   newTransfert.setAmount(transferDto.getAmount());
   newTransfert.setCurrency(transferDto.getCurrency());
   newTransfert.setDate(new Date());
   newTransfert.setDescription(transferDto.getDescription());
   newTransfert.setFee(0.005);
   newTransfert.setRecipient(recipientUser);
   newTransfert.setSourceUser(connectedUser);
   
 //problem ici field"source-user doesnt have a default value sql
    return transfertRepository.save(newTransfert);
  }



}
