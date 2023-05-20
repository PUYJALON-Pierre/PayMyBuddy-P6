package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity @NoArgsConstructor @Table(name = "user_account")
public class UserAccount implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2870189363034950533L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  @Column(name = "user_account_id")
  private int userAccountID;

  @Column(name = "email", unique = true, length=50, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "last_connection")
  private Date lastConnection;

  @Column(name = "online_status")
  private boolean onlineStatus;



  
  
  
  
}
