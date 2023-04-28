package com.paymybuddy.pay_my_buddy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity @NoArgsConstructor @Table(name = "user")
public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3191940228131553256L;

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "user_id")
  private int userID;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "birthdate")
  private Date birthdate;

  @ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "friend", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "friend_id", nullable = false))
  private List<User> friendList;

  @OneToMany(mappedBy = "sourceUser", fetch = FetchType.LAZY)
  private List<Deposit> depositList;

  @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
  private List<Transfert> transfertList;

}
