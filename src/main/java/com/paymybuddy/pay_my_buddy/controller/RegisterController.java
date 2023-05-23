package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.service.MyUserDetailsService;

import jakarta.validation.Valid;


@Controller
public class RegisterController {

  @Autowired
  MyUserDetailsService myUserDetailsService;
  
  @GetMapping(value= "/registration")
  
public String viewRegisterPageModel (Model model) {
    model.addAttribute("registerForm", new RegisterDTO());
    
    return "register";
  }

  
  
@PostMapping("/registration")
  public String registerUser(@Valid @ModelAttribute("registerForm") RegisterDTO register, BindingResult bindingResult, Model model) {
   
  if(bindingResult.hasErrors()) return "register";
  try{
    myUserDetailsService.registerUser(register);
  
  } catch (UserAccountException e) {
    bindingResult.rejectValue("email", e.getMessage(), e.getMessage());
    return "register";
  }
  return "redirect:/login";
}
}
