package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    return transfertRepository.findBySourceUserOrRecipient(user1, user2, PageRequest.of(page, 3, Sort.by("date").descending()));
  }

  @Override
  @Transactional
  public Transfert saveTransfert(Transfert transfert) {

    return transfertRepository.save(transfert);
  }

  @Override
  @Transactional
  public void deleteTransfertById(int id) {
    transfertRepository.deleteById(id);

  }

  @Override
  @Transactional
  public void deleteTransfertBySourceUser(User sourceUser) {
    transfertRepository.deleteBySourceUser(sourceUser);

  }

  @Override
  @Transactional
  public Transfert createTransfert(User connectedUser, TransferDTO transferDto)
      throws UserBalanceException, UserAccountException {

    double amount = transferDto.getAmount();

    // Checking that amount is not negative before transfert
    if (transferDto.getAmount() <= 0) {
      throw new UserBalanceException("You can't make a transfert with 0 or negative amount");

    }

    // Checking that recipient is not null and exist
    if (userAccountRepository.findByEmail(transferDto.getRecipient()).isEmpty()) {

      throw new UserAccountException("Recipient of this transfer cannot be find");
    }

    // Retrieving user from email
    User recipientUser = userRepository
        .findByUserAccount(userAccountRepository.findByEmail(transferDto.getRecipient()).get())
        .get();

    // Checking if User balance is enough for transfer
    double amountWithFee = amount + (amount * 0.005);

    if (amountWithFee > connectedUser.getAppAccount().getBalance()) {

      throw new UserBalanceException(
          "You dont have enough money on your app account to make this transfer");

    }

    // Substract amount on user balance
    if (connectedUser.getAppAccount().getBalance() >= amountWithFee) {

      AppAccount updateAppAccount = connectedUser.getAppAccount();
      double result = updateAppAccount.getBalance() - (amountWithFee);
      double arrondi = Math.round(result * 100.0) / 100.0;
      updateAppAccount.setBalance(arrondi);

      connectedUser.setAppAccount(updateAppAccount);

      // Add credit to recipient user balance
      recipientUser.getAppAccount().setBalance(recipientUser.getAppAccount().getBalance() + amount);

    }

    // Saving both users
    userRepository.save(recipientUser);
    userRepository.save(connectedUser);

    // Setting new transfert
    Transfert newTransfert = new Transfert();

    newTransfert.setAmount(transferDto.getAmount());
    newTransfert.setCurrency(transferDto.getCurrency());
    newTransfert.setDate(new Date());
    newTransfert.setDescription(transferDto.getDescription());
    newTransfert.setFee(0.005);
    newTransfert.setRecipient(recipientUser);
    newTransfert.setSourceUser(connectedUser);

    // Saving transfert
    return transfertRepository.save(newTransfert);
  }

}
