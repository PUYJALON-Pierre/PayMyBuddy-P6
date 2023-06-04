package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for AppAccount in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
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

}
