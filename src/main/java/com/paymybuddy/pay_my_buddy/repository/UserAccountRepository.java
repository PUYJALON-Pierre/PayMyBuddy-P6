package com.paymybuddy.pay_my_buddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer>{


public Optional<UserAccount> findById(int id);
  

public Optional<UserAccount> findByEmail(String email);


public Optional<UserAccount> deleteById(int id);


public Optional<UserAccount> deleteByEmail(String email);


}
