package com.paymybuddy.pay_my_buddy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.model.MyUserPrincipal;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  UserAccountRepository userAccountRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public MyUserDetailsService(UserAccountRepository userAccountRepository) {
    super();
    this.userAccountRepository = userAccountRepository;
  }

  public User saveUser(RegisterDTO registerDTO) {

    UserAccount newAccount = new UserAccount();
    newAccount.setEmail(registerDTO.getEmail());
    newAccount.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

    User newUser = new User(registerDTO.getFirstName(), registerDTO.getLastName(),
        registerDTO.getBirthdate(), newAccount);

    return userRepository.save(newUser);

  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

    UserAccount userAccount = userAccountRepository.findByEmail(mail).get();

    if (userAccount == null)

    {
      System.out.println("Cannot find email : " + mail);
    }

    return new MyUserPrincipal(userAccount);
  }

}
