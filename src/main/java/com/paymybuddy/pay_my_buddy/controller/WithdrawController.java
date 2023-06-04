package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.pay_my_buddy.DTO.DepositDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.IDepositService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.validation.Valid;

/**
 * Controller class for Withdraw in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class WithdrawController {

  @Autowired
  IDepositService iDepositService;

  @Autowired
  IUserService iUserService;

  /**
   * Get withdraw page model
   *
   * @param model - Model
   * @return deposit (html template)
   */
  @GetMapping(value = "/withdraw")

  public String viewWithdrawPageModel(Model model) {
    model.addAttribute("withdrawForm", new DepositDTO());

    return "withdraw";
  }

  /**
   * Creating a new deposit
   *
   * @param model - Model
   * @param deposit - DepositDTO
   * @param bindingResult - BindingResult
   * @return profile or withdraw (html template)
   */
  @PostMapping("/withdraw")
  public String withdraw(@Valid @ModelAttribute("withdraw") DepositDTO deposit,
      BindingResult bindingResult, Model model) throws UserBalanceException {

    if (bindingResult.hasErrors())
      return "withdraw";
    try {

      User userToUpdate = iUserService.getConnectedUser();

      iDepositService.saveWithdraw(userToUpdate, deposit);

    } catch (UserAccountException e) {
      bindingResult.rejectValue("amount", e.getMessage(), e.getMessage());
      return "withdraw";
    }
    return "redirect:/profile";
  }

}
