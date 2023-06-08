package com.paymybuddy.pay_my_buddy.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class MyUserDetailsConfigTest {

  @Bean
  public UserDetailsService MyUserDetailsService() {
    User userTest = new User("testuser@email.com", "password", new ArrayList<>());
    return new InMemoryUserDetailsManager(Arrays.asList(userTest));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
