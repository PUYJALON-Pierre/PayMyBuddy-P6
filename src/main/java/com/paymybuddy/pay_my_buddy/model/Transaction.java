package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for Transaction in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Data @NoArgsConstructor @MappedSuperclass
public class Transaction implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7243771762789741345L;

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
  private int id;

  @Column(name = "amount")
  private double amount;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "source_user", nullable = false)
  private User sourceUser;

  @Column(name = "description")
  private String description;

  @Column(name = "fee")
  private double fee;

  @Column(name = "currency", nullable = false)
  private String currency;

  @Column(name = "date", nullable = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date date;

}
