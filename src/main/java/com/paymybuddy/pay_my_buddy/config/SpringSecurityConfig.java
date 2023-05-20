package com.paymybuddy.pay_my_buddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration @EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {


  private final JwtAuthentificationFilter jwtAuthFilter;
  private final DaoAuthenticationProvider authenticationProvider;
 
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  
  
    http.csrf()
    .disable()
    .authorizeHttpRequests()
    .requestMatchers("/css/**", "/images/**").permitAll()
    .requestMatchers("/home", "/contact", "/registration")
    .permitAll()
    .requestMatchers("/api/v1/auth/**")
    .permitAll()
    .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/profile", true)
        .passwordParameter("password")
        .usernameParameter("username")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
.and().authenticationProvider(authenticationProvider)
.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    
    return http.build();
  }


//***************************************************************
  
  

 
 

}