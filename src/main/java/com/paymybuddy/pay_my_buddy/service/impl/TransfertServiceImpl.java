package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.TransfertRepository;
import com.paymybuddy.pay_my_buddy.service.ITransfertService;

@Service
public class TransfertServiceImpl implements ITransfertService {

  
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

    return transfertRepository.findBySourceUserOrRecipient(user1, user2, PageRequest.of(page, 3));
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



}
