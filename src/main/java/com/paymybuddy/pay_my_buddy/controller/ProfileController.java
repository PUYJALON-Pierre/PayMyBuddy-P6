package com.paymybuddy.pay_my_buddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.service.IUserAccountService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

@Controller
public class ProfileController {

  @Autowired
IUserService iUserService;
  @Autowired
  IUserAccountService iUserAccountService;
 
  
  
  @GetMapping(value= "/profile")
public String viewProfilePageModel (Model model, @RequestParam(name="page", defaultValue="0") int currentPageFriends,
    @RequestParam(name="pageUsers", defaultValue="0") int currentPageUsers) throws UserAccountException {
  
    User connectedUser = iUserService.getConnectedUser();
    
    List<User>friendList= iUserService.findAllFriend(connectedUser);
  
    //need sublist or else return all friends at once
    Pageable pageable = PageRequest.of(currentPageFriends, 5);
    int start = (int)pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), friendList.size());
 
    Page<User> friendsPages = new PageImpl<User>(friendList.subList(start, end), pageable, friendList.size());
  
    Page<User> allUsers =iUserService.getUsers(currentPageUsers);
    
    //filtering user and people already friends
    
 
    model.addAttribute("connectedUser", connectedUser);
    model.addAttribute("friendsPages", friendsPages.getContent());
    model.addAttribute("pages", new int[friendsPages.getTotalPages()]);
    model.addAttribute("currentPage", currentPageFriends); 
    
    model.addAttribute("allUsers", allUsers.getContent());
    model.addAttribute("pagesUsers", new int[allUsers.getTotalPages()]);
    model.addAttribute("currentPageUsers", currentPageUsers); 
    
    return "profile";
    
  }
  
  
  
  @GetMapping(value= "/userSearch")
  public String search(Model model, @RequestParam(name="page", defaultValue="0") int currentPage,
      @RequestParam(name="keyWord", defaultValue="") String email) throws UserAccountException {
 String userSearch ="Not found";
    try {
   User user = iUserService.getUserByAppAcount(iUserAccountService.findUserByEmail(email));
       userSearch = user.getUserAccount().getEmail();}catch (Exception e) {
    
     return "profile";
    }

model.addAttribute("userSearch", userSearch);
model.addAttribute("keyWord", email);

return "profile";




}
  
  @PostMapping(value="/deletefriend")
  public String deleteFriend(int id) throws FriendException, UserAccountException  {
 
    
    User connectedUser = iUserService.getConnectedUser();
    
    User friend = iUserService.getUserById(id);
    iUserService.deleteFriend(connectedUser, friend);


    
    return "redirect:profile";




}

  @PostMapping(value="/addfriend")
  public String addFriend(int id) throws FriendException, UserAccountException  {
 
    
    User connectedUser = iUserService.getConnectedUser();
    
    User friend = iUserService.getUserById(id);
    iUserService.addFriendToUser(connectedUser, friend);

    return "redirect:profile";

}
  
  
  
}
