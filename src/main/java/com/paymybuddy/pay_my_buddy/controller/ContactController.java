package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for contact view in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class ContactController {

  /**
   * Get contact page model
   *
   * @param model - Model
   * @return contact (html template)
   */
  @GetMapping(value = "/contact")
  public String viewContactPageModel(Model model) {

    return "contact";
  }

}
