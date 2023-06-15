package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.DepositRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IDepositService;

import jakarta.transaction.Transactional;

@Service
public class DepositServiceImpl implements IDepositService {

  @Autowired
  UserRepository userRepository;

  DepositRepository depositRepository;

  public DepositServiceImpl(DepositRepository depositRepository) {
    super();
    this.depositRepository = depositRepository;
  }

  @Override
  public List<Deposit> getDeposits() {

    return depositRepository.findAll();
  }

  @Override
  public Page<Deposit> getDepositBySourceUser(User sourceUser, int page) {

    return depositRepository.findBySourceUser(sourceUser, PageRequest.of(page, 3));
  }

  @Override
  public Optional<Deposit> getDepositById(int id) {

    return depositRepository.findById(id);
  }

 
  @Override
  @Transactional
  public void deleteDepositById(int id) {

    depositRepository.deleteById(id);

  }
  
  @Override
  @Transactional
  public Deposit saveDeposit(User connectedUser, DepositDTO deposit) {

    Deposit depositToSave = new Deposit();

    depositToSave.setBankAccountIBAN(deposit.getIban());
    depositToSave.setAmount(deposit.getAmount());
    depositToSave.setCurrency(deposit.getCurrency());
    depositToSave.setDate(new Date());
    depositToSave.setDescription(deposit.getDescription());
    depositToSave.setFee(0.005);
    depositToSave.setSourceUser(connectedUser);
    depositToSave.setOperator("+");

    double amountWithFeetoPay = deposit.getAmount() + (deposit.getAmount() * 0.005);

    // set balance account of user with amount
    AppAccount updateAppAccount = connectedUser.getAppAccount();
    double result = updateAppAccount.getBalance() + (deposit.getAmount());
    double arrondi = Math.round(result * 100.0) / 100.0;
    updateAppAccount.setBalance(arrondi);

    connectedUser.setAppAccount(updateAppAccount);
    userRepository.save(connectedUser);

    return depositRepository.save(depositToSave);
  }
  
  
  @Override
  @Transactional
  public Deposit saveWithdraw(User connectedUser, DepositDTO deposit) throws UserBalanceException {

    Deposit depositToSave = new Deposit();

    depositToSave.setBankAccountIBAN(deposit.getIban());
    depositToSave.setAmount(deposit.getAmount());
    depositToSave.setCurrency(deposit.getCurrency());
    depositToSave.setDate(new Date());
    depositToSave.setDescription(deposit.getDescription());
    depositToSave.setFee(0.005);
    depositToSave.setSourceUser(connectedUser);
    depositToSave.setOperator("-");

    // checking if balance is enough for withdraw
    double amountWithFeetoPay = deposit.getAmount() + (deposit.getAmount() * 0.005);

    if (amountWithFeetoPay > connectedUser.getAppAccount().getBalance()) {

      throw new UserBalanceException(
          "You dont have enough money on your app account to make this transfer");

    }

    AppAccount updateAppAccount = connectedUser.getAppAccount();
    double result = updateAppAccount.getBalance() - (amountWithFeetoPay);
    double arrondi = Math.round(result * 100.0) / 100.0;
    updateAppAccount.setBalance(arrondi);

    connectedUser.setAppAccount(updateAppAccount);
    userRepository.save(connectedUser);

    return depositRepository.save(depositToSave);

  }

}
