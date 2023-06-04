package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model class for User in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data @ToString @Builder @AllArgsConstructor @Entity @NoArgsConstructor @Table(name = "user")
public class User implements Serializable, UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = -3191940228131553256L;

  public User(String firstname, String lastname, Date birthdate, UserAccount userAccount,
      AppAccount appAccount) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthdate = birthdate;
    this.userAccount = userAccount;
    this.appAccount = appAccount;

  }

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id")
  private int userID;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "birthdate") @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthdate;

  @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "friend", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "friend_id", nullable = false)
  // uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}) duplicate when
  // rerun
  )

  private List<User> friendList;

  @OneToMany(mappedBy = "sourceUser", fetch = FetchType.LAZY)
  private List<Deposit> depositList;

  @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
  private List<Transfert> transfertList;

  @OneToOne @JoinColumn(name = "user_account_id")
  private UserAccount userAccount;

  @OneToOne @JoinColumn(name = "app_account_id", nullable = false)
  private AppAccount appAccount;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("USER"));

  }

  @Override
  public String getPassword() {
    return userAccount.getPassword();
  }

  @Override
  public String getUsername() {
    return userAccount.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
