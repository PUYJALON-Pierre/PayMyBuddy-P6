package com.paymybuddy.pay_my_buddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of Pay My Buddy Web Application. The aim of this application is to simplify money
 * transfers for people in order to pay their friends or handle their finances. Turning money
 * transfer that can be inconvenient into easy, secured and quick transaction.
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 * @version 1.0.0
 */
@SpringBootApplication
public class PayMyBuddyApplication {

  public static void main(String[] args) {
    SpringApplication.run(PayMyBuddyApplication.class, args);
  }

}
