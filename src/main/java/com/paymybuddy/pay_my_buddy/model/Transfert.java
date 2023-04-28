package com.paymybuddy.pay_my_buddy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table (name ="transfert")
public class Transfert extends Transaction {

  
  /**
   * 
   */
  private static final long serialVersionUID = 6114246568584325063L;
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn (name ="user_recipient", insertable=false, updatable=false, nullable = false)
  private User recipient;
  

}
