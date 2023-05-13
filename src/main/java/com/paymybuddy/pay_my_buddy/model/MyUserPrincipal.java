package com.paymybuddy.pay_my_buddy.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails{

  
 
private UserAccount userAccount;


public MyUserPrincipal(UserAccount userAccount) {
    this.userAccount = userAccount;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {

  return Collections.singletonList(new SimpleGrantedAuthority("USER"));
}


@Override
public String getPassword() {
  // TODO Auto-generated method stub
  return userAccount.getPassword();
}


@Override
public String getUsername() {
  // TODO Auto-generated method stub
  return userAccount.getEmail();
}


@Override
public boolean isAccountNonExpired() {
  // TODO Auto-generated method stub
  return true;
}


@Override
public boolean isAccountNonLocked() {
  // TODO Auto-generated method stub
  return true;
}


@Override
public boolean isCredentialsNonExpired() {
  // TODO Auto-generated method stub
  return true;
}


@Override
public boolean isEnabled() {
  // TODO Auto-generated method stub
  return true;
}
  
  
}
