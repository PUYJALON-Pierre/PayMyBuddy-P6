package com.paymybuddy.pay_my_buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.pay_my_buddy.DTO.RegisterDTO;
import com.paymybuddy.pay_my_buddy.DTO.UpdateDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.validation.Valid;

/**
 * Controller class for Updating connected user profile in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class UpdateController {

  @Autowired
  IUserService iUserService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Get update page model for connected user
   *
   * @param model - Model
   * @return update (html template)
   */
  @GetMapping(value = "/update")

  public String viewUpdatePageModel(Model model) {
    model.addAttribute("updateForm", new RegisterDTO());

    return "update";
  }

  /**
   * Update userConnected Profile
   *
   * @param model - Model
   * @param update - UpdateDTO
   * @param bindingResult - BindingResult
   * @return profile or update (html template)
   */
  @PostMapping("/update")
  public String updateUserConnected(@Valid @ModelAttribute("update") UpdateDTO update,
      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors())
      return "update";
    try {
      User userToUpdate = iUserService.getConnectedUser();
      userToUpdate.setFirstname(update.getFirstname());
      userToUpdate.setLastname(update.getLastname());
      userToUpdate.setBirthdate(update.getBirthdate());
      userToUpdate.getUserAccount().setPassword(passwordEncoder.encode(update.getPassword()));

      iUserService.saveUser(userToUpdate);

    } catch (UserAccountException e) {
      bindingResult.rejectValue("email", e.getMessage(), e.getMessage());
      return "update";
    }
    return "redirect:/profile";
  }

}
