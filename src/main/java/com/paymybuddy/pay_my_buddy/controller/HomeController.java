package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class HomeController {

  
  @GetMapping(value= "/home")
public String viewHomePageModel (Model model) {
  
    return "home";
  }
  
  
  @RequestMapping("/")
  public String defaut() {
    return "redirect:/home";
  }
  
  

  
}

