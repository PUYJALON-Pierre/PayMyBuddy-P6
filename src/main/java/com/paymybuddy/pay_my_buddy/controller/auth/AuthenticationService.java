package com.paymybuddy.pay_my_buddy.controller.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.config.JwtService;
import com.paymybuddy.pay_my_buddy.model.AppAccount;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.model.UserAccount;
import com.paymybuddy.pay_my_buddy.repository.AppAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  
  private final AppAccountRepository appAccountRepository;
  
  private final UserRepository userRepository;
  
  private final UserAccountRepository userAccountRepository;
  
  private final JwtService jwtService;
  
  private final PasswordEncoder passwordEncoder;
  
  private final AuthenticationManager authenticationManager;
  
  
  
  
  public AuthenticationResponse register(RegisterDTO registerDTO) {

    AppAccount newAppAccount = new AppAccount();
    newAppAccount.setBalance(0);
    //save to get id (by JPA)??
    appAccountRepository.save(newAppAccount);
    
    UserAccount newAccount = new UserAccount();
    newAccount.setEmail(registerDTO.getEmail());
    newAccount.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    //save to get id (by JPA)??
    userAccountRepository.save(newAccount);
 
    var user = new User(registerDTO.getFirstName(), registerDTO.getLastName(),
        registerDTO.getBirthdate(), newAccount, newAppAccount);
    
    userRepository.save(user);
    
 
    var jwtToken = jwtService.generateToken(user); //besoin de user details donc extend userdetails mais quand extend user details ne retouve pas user avec findByemail mais seulement useraccount
    
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
  
  
  

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getEmail()));
 
 UserAccount userAccount = userAccountRepository.findByEmail(request.getEmail()).orElseThrow();
 
 var user = userRepository.findByUserAccount(userAccount).get();
 
 var jwtToken = jwtService.generateToken(user);
 
 return AuthenticationResponse.builder().token(jwtToken).build();
  }

  
  
  
}
