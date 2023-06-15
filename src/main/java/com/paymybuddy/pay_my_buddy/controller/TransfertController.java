package com.paymybuddy.pay_my_buddy.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.pay_my_buddy.DTO.TransferDTO;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.ITransfertService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.validation.Valid;

/**
 * Controller class for Transfer in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class TransfertController {

  @Autowired
  ITransfertService iTransfertService;

  @Autowired
  IUserService iUserService;

  /**
   * Get transfer page model for connected user
   *
   * @param model - Model
   * @param currentPageUsers - int
   * @param currentPageDeposit - int
   * @return transfer (html template)
   */
  @GetMapping(value = "/transfer")
  public String viewTransferPageModel(Model model,
      @RequestParam(name = "page", defaultValue = "0") int currentPage,
      @RequestParam(name = "pageDeposit", defaultValue = "0") int currentPageDeposit)
      throws UserAccountException {

    // Retrieve transfert for connectedUser
    User connectedUser = iUserService.getConnectedUser();

    Page<Transfert> pageTransferts = iTransfertService.getTransfertsBetweenAnyUsers(connectedUser,
        connectedUser, currentPage);

    model.addAttribute("connectedUser", connectedUser);
    model.addAttribute("transfertsPages", pageTransferts.getContent());
    model.addAttribute("pages", new int[pageTransferts.getTotalPages()]);
    model.addAttribute("currentPage", currentPage);

    // Create page of deposits with a sublist
    List<Deposit> depositsList = iUserService.findAllDeposit(connectedUser);
    // To sort deposit by more recent
    Collections.reverse(depositsList);
    Pageable pageable1 = PageRequest.of(currentPageDeposit, 5);
    int start1 = (int) pageable1.getOffset();
    int end1 = Math.min((start1 + pageable1.getPageSize()), depositsList.size());

    Page<Deposit> pageDeposits = new PageImpl<Deposit>(depositsList.subList(start1, end1),
        pageable1, depositsList.size());

    model.addAttribute("depositsPages", pageDeposits);
    model.addAttribute("pagesDeposit", new int[pageDeposits.getTotalPages()]);
    model.addAttribute("currentPageDeposit", currentPageDeposit);

    model.addAttribute("transferForm", new Transfert());

    // Retrieve friends of connectedUser
    List<User> connectionsList = iUserService.findAllFriend(connectedUser);
    model.addAttribute("connectionsList", connectionsList);

    return "transfer";
  }

  /**
   * Create a new transfer
   *
   * @param model - Model
   * @param transferDto - TransferDTO
   * @param bindingResult - BindingResult
   * @return transfer (html template)
   */
  @PostMapping(value = "/transfer")
  public String makeTransfert(@Valid @ModelAttribute("transferForm") TransferDTO transferDto,
      BindingResult bindingResult, Model model) throws UserAccountException {
    User connectedUser = iUserService.getConnectedUser();

    if (bindingResult.hasErrors())
      return "transfer";
    try {
      iTransfertService.createTransfert(connectedUser, transferDto);

    } catch (UserBalanceException e) {

      bindingResult.rejectValue("amount", e.getMessage(), e.getMessage());
      return "transfer";
    }

    return "redirect:/transfer";
  }

}
