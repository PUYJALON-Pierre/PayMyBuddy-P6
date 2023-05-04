package com.paymybuddy.pay_my_buddy.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  public Page <User> findAll( Pageable page);
  
  public Optional<User> deleteById(int id);
  
  
  
  
  
}
