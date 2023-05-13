package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

  
  @GetMapping(value= "/profile")
public String viewProfilePageModel (Model model) {
  
    return "profile";
    
  }
  
}
