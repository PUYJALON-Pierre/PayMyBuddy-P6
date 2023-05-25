package com.paymybuddy.pay_my_buddy.controller;

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

import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.exception.UserBalanceException;
import com.paymybuddy.pay_my_buddy.model.Deposit;
import com.paymybuddy.pay_my_buddy.model.Transfert;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.ITransfertService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

import jakarta.validation.Valid;

@Controller
public class TransfertController {
 
  @Autowired
ITransfertService iTransfertService;
  
  
  @Autowired
IUserService iUserService;
  
  @GetMapping(value= "/transfer")
public String viewTransferPageModel (Model model, @RequestParam(name="page", defaultValue="0") int currentPage) throws UserAccountException {
  
    User connectedUser = iUserService.getConnectedUser();
    
    List<Transfert>transfertsList= iUserService.findAllTransfert(connectedUser);
    
  //need sublist or else return all friends at once
    Pageable pageable = PageRequest.of(currentPage, 5);
    int start = (int)pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), transfertsList.size());

    Page<Transfert> pageTransferts = new PageImpl<Transfert>(transfertsList.subList(start, end), pageable, transfertsList.size());
   
   model.addAttribute("connectedUser", connectedUser);
   model.addAttribute("transfertsPages",pageTransferts.getContent());
   model.addAttribute("pages", new int[pageTransferts.getTotalPages()]);
   model.addAttribute("currentPage", currentPage); 
   
 
   
   List<Deposit>depositsList= iUserService.findAllDeposit(connectedUser);
   
 /*need sublist or else return all friends at once
   Pageable pageable1 = PageRequest.of(currentPage, 5);
   int start1 = (int)pageable1.getOffset();
   int end1 = Math.min((start1 + pageable1.getPageSize()), depositsList.size());

   Page<Deposit> pageDeposits = new PageImpl<Deposit>(depositsList.subList(start1, end1), pageable1, depositsList.size());*/
   
   model.addAttribute("depositsPages",depositsList);
  // model.addAttribute("pages", new int[pageDeposits.getTotalPages()]);
   
   //for post
   model.addAttribute("transferForm", new Transfert());
   
   List<User>connectionsList= iUserService.findAllFriend(connectedUser);
   model.addAttribute("connectionsList", connectionsList);
   
    return "transfer";
}
  
  
  
  @PostMapping(value= "/transfer")
  public String makeTransfert(@Valid @ModelAttribute("transferForm") Transfert transfert, BindingResult bindingResult, Model model) throws UserAccountException {
    User connectedUser = iUserService.getConnectedUser();
    
    if(bindingResult.hasErrors())return "transfer";
    try {
      iTransfertService.createTransfert(connectedUser, transfert);
      
    }catch(UserBalanceException e) {
      
      bindingResult.rejectValue("amount",  e.getMessage(), e.getMessage());
      return "transfer";
    }
  
    return "redirect:/transfer";
  }
  
  
  
}







