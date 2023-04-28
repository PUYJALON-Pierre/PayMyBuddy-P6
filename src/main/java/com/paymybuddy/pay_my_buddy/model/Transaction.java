package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Transaction implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7243771762789741345L;

  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  
  @Column(name="amount")
  private double amount;
  
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn(name ="source_user", insertable=false, updatable=false, nullable = false)
  private User sourceUser;
  
  @Column(name="description")
  private String description;
  
  @Column(name="fee")
  private double fee;
  
  @Column(name="currency", nullable = false)
  private String currency;
  
  @Column(name="date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar dateTime;
  
 
  
}
