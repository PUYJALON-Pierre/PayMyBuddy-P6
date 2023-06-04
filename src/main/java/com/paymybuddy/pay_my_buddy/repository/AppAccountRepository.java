package com.paymybuddy.pay_my_buddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.pay_my_buddy.model.AppAccount;

/**
 * Repository of AppAccount in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Integer> {

}
