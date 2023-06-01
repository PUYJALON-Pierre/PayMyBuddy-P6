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
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.IDepositService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.validation.Valid;

@Controller
public class DepositController {

  @Autowired
  IDepositService iDepositService;
  
  @Autowired
  IUserService iUserService;
  
  @GetMapping(value = "/deposit")

  public String viewDepositPageModel(Model model) {
    model.addAttribute("depositForm", new DepositDTO());

    return "deposit";
  }

  @PostMapping("/deposit")
  public String deposit(@Valid @ModelAttribute("deposit") DepositDTO deposit,
      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors())
      return "deposit";
    try {
      
      User userToUpdate = iUserService.getConnectedUser();

iDepositService.saveDeposit(userToUpdate, deposit);

    } catch (UserAccountException e) {
      bindingResult.rejectValue("email", e.getMessage(), e.getMessage());
      return "deposit";
    }
    return "redirect:/profile";
  }
  
  
}
