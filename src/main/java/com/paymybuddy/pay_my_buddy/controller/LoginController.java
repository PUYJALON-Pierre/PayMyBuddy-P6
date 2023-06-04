package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for Login in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class LoginController {

  /**
   * Get login page model
   *
   * @param model - Model
   * @return login (html template)
   */
  @GetMapping(value = "/login")
  public String viewLoginPageModel(Model model) {

    return "login";
  }

}
