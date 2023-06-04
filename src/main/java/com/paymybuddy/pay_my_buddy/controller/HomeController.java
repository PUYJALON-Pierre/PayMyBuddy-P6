package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for HomePage in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class HomeController {

  /**
   * Get home page model
   *
   * @param model - Model
   * @return home (html template)
   */
  @GetMapping(value = "/home")
  public String viewHomePageModel(Model model) {

    return "home";
  }

  /**
   * Get redirection to home page model
   *
   * @param model - Model
   * @return home (html template)
   */
  @RequestMapping("/")
  public String defaut() {
    return "redirect:/home";
  }

}
