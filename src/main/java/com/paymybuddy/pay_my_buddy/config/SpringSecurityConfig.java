package com.paymybuddy.pay_my_buddy.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.paymybuddy.pay_my_buddy.repository.UserAccountRepository;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

/**
 * Class to configure Spring Security for Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Configuration @EnableWebSecurity
public class SpringSecurityConfig {

  
  @Autowired
  private UserAccountRepository userAccountRepository;
  
  
//***************************************************************
  
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
  
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  
  
    http.csrf()
    .disable()
    .authorizeHttpRequests()
    .requestMatchers("/css/**", "/images/**").permitAll()
    .requestMatchers("/home", "/contact", "/registration")
    .permitAll()
    .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .passwordParameter("password")
        .usernameParameter("email")
        .defaultSuccessUrl("/profile", true)
.and().rememberMe(rememberMeConfigurer -> rememberMeConfigurer.userDetailsService(userDetailsService())
    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(1))
    .key("645367566B5970337336763979244226452948404D6351665468576D5A713474")
    .rememberMeParameter("remember-me"))
.logout()
     .clearAuthentication(true)
     .invalidateHttpSession(true)
     .deleteCookies("JSESSIONID", "remember-me")
     .logoutSuccessUrl("/home");;
    
    return http.build();
  }


}