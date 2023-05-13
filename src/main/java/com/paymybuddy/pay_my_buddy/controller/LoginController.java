package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class LoginController {

  
  
  @RolesAllowed("USER")
  @RequestMapping("/*")
  public String getUser()
  {
     return "Welcome User";
  }

  @RolesAllowed({"USER","ADMIN"})
  @RequestMapping("/admin")
  public String getAdmin()
  {
     return "Welcome Admin";
  }
  

  
}
