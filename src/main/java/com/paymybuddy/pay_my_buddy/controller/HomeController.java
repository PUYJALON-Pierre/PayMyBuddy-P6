package com.paymybuddy.pay_my_buddy.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
  
  final static Logger logger = LogManager.getLogger(HomeController.class);
  
  @GetMapping(value= "/home")
public String viewHomePageModel (Model model) {
  
    return "home";
    
  }
  
  
}
