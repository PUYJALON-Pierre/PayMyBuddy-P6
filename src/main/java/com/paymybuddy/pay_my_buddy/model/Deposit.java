package com.paymybuddy.pay_my_buddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Model class for Deposit in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data @EqualsAndHashCode(callSuper = false) @NoArgsConstructor @Entity @Table(name = "deposit")
public class Deposit extends Transaction {

  /**
   * 
   */
  private static final long serialVersionUID = -7250168636809312214L;

  @Column(name = "bank_account_IBAN", unique = true, length = 50, nullable = false)
  private String bankAccountIBAN;

  @Column(name = "operator")
  private String operator;

}
