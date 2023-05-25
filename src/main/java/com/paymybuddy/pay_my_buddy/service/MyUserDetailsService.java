package com.paymybuddy.pay_my_buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  AppAccountRepository appAccountRepository;
  
  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  public MyUserDetailsService(UserAccountRepository userAccountRepository) {
    super();
    this.userAccountRepository = userAccountRepository;}


  
  public User registerUser(RegisterDTO registerDTO) throws UserAccountException {

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);
    
    //save to generate id by jpa
    appAccountRepository.save(newAppAccount);
    
    UserAccount newAccount = new UserAccount();
    newAccount.setEmail(registerDTO.getEmail());
    newAccount.setOnlineStatus(true);
    newAccount.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    
    User newUser = new User(registerDTO.getFirstname(), registerDTO.getLastname(),
        registerDTO.getBirthdate(), newAccount, newAppAccount);

    if (userAccountRepository.findByEmail(newUser.getUserAccount().getEmail()).isPresent()) {
      throw new UserAccountException("An account has already registred with this Email");

    } else {
      //save to generate id by jpa
      userAccountRepository.save(newAccount);
      
      userRepository.save(newUser);
      return newUser;
    }

  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

    UserAccount userAccount = userAccountRepository.findByEmail(mail).get();

    if (userAccount == null)

    {
      System.out.println("Cannot find email : " + mail);
    }
    
    //use userAcount to retrieve user that implements UserDetails
    User connectedUSer = userRepository.findByUserAccount(userAccount).get();
    

    return connectedUSer;
  }

  
  
  
}
