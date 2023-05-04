package com.paymybuddy.pay_my_buddy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.DepositRepository;
import com.paymybuddy.pay_my_buddy.service.IDepositService;

@Service
public class DepositServiceImpl implements IDepositService {

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
  public void deleteDepositById(int id) {

    depositRepository.deleteById(id);

  }

  @Override
  public void deleteDepositBySourceUser(User user) {

    depositRepository.deleteBySourceUser(user);

  }

  @Override
  public Deposit saveDeposit(Deposit deposit) {

    return depositRepository.save(deposit);
  }

}
