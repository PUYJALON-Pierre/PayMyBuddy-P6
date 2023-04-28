package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data @Entity @NoArgsConstructor @Table(name = "app_account")
public class AppAccount implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 894772764950354657L;

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "app_account_id")
  private int appAccountID;

  @Column(name = "balance")
  private double balance;

  @OneToOne @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
