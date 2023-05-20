package com.paymybuddy.pay_my_buddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  @Autowired
  private UserAccountRepository userAccountRepository;
  
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

    return config.getAuthenticationManager();
  }
  
  
  @Bean
  public UserDetailsService userDetailsService() {
      return new MyUserDetailsService(userAccountRepository);
  }
  
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//***************************************************************
}
