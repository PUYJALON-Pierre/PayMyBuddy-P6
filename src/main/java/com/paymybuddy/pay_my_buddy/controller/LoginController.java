package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@Controller
public class LoginController {

  
  @GetMapping(value= "/login")
public String viewLoginPageModel (Model model) {
  
    return "login";
}
  
  


  
}
