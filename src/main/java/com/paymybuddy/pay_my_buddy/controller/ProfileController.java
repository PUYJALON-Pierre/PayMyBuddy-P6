package com.paymybuddy.pay_my_buddy.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.pay_my_buddy.exception.FriendException;
import com.paymybuddy.pay_my_buddy.exception.UserAccountException;
import com.paymybuddy.pay_my_buddy.model.User;
import com.paymybuddy.pay_my_buddy.repository.UserRepository;
import com.paymybuddy.pay_my_buddy.service.IUserAccountService;
import com.paymybuddy.pay_my_buddy.service.IUserService;

/**
 * Controller class for user Profile in Pay My Buddy Application
 *
 * @author PUYJALON Pierre
 * @since 03/06/2023
 */
@Controller
public class ProfileController {

  @Autowired
  IUserService iUserService;
  @Autowired
  IUserAccountService iUserAccountService;

  @Autowired
  UserRepository userRepository;

  /**
   * Get profile page model for connected user
   *
   * @param model - Model
   * @param currentPageFriends - int
   * @param currentPageUsers - int
   * @return profile (html template)
   */
  @GetMapping(value = "/profile")
  public String viewProfilePageModel(Model model,
      @RequestParam(name = "page", defaultValue = "0") int currentPageFriends,
      @RequestParam(name = "pageUser", defaultValue = "0") int currentPageUsers)
      throws UserAccountException {

    User connectedUser = iUserService.getConnectedUser();
    List<User> friendList = iUserService.findAllFriend(connectedUser);

    model.addAttribute("connectedUser", connectedUser);

    // use sublist to create page from friendList
    Pageable pageable = PageRequest.of(currentPageFriends, 5);
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), friendList.size());
    Page<User> friendsPages = new PageImpl<User>(friendList.subList(start, end), pageable,
        friendList.size());

    model.addAttribute("friendsPages", friendsPages.getContent());
    model.addAttribute("pages", new int[friendsPages.getTotalPages()]);
    model.addAttribute("currentPage", currentPageFriends);

    List<Integer> friendListID = new ArrayList<>();

    friendListID.add(connectedUser.getUserID());
    friendList.forEach(f -> {
      friendListID.add(f.getUserID());
    });

    Pageable pageable2 = PageRequest.of(currentPageUsers, 5);

    Page<User> potentialFriendsPage = iUserService.getPotentialFriends(friendListID, pageable2);

    model.addAttribute("allUsers", potentialFriendsPage.getContent());
    model.addAttribute("pagesUsers", new int[potentialFriendsPage.getTotalPages()]);
    model.addAttribute("currentPageUsers", currentPageUsers);

    return "profile";

  }

  /**
   * 
   * @param model
   * @param currentPageFriends
   * @param currentPageUsers
   * @param email
   * @return
   * @throws UserAccountException
   */
  @GetMapping(value = "/userSearch")
  public String searchFriend(Model model,
      @RequestParam(name = "page", defaultValue = "0") int currentPageFriends,
      @RequestParam(name = "pageUser", defaultValue = "0") int currentPageUsers,
      @RequestParam(name = "keyWord", defaultValue = "") String email) throws UserAccountException {

    // Recharge profile page
    User connectedUser = iUserService.getConnectedUser();
    List<User> friendList = iUserService.findAllFriend(connectedUser);

    model.addAttribute("connectedUser", connectedUser);

    // use sublist to create page from friendList
    Pageable pageable = PageRequest.of(currentPageFriends, 5);
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), friendList.size());
    Page<User> friendsPages = new PageImpl<User>(friendList.subList(start, end), pageable,
        friendList.size());

    model.addAttribute("friendsPages", friendsPages.getContent());
    model.addAttribute("pages", new int[friendsPages.getTotalPages()]);
    model.addAttribute("currentPage", currentPageFriends);

    // Creating userSearch Page
    List<Integer> friendListID = new ArrayList<>();

    friendListID.add(connectedUser.getUserID());
    friendList.forEach(f -> {
      friendListID.add(f.getUserID());
    });
    
    Pageable pageable3 = PageRequest.of(currentPageUsers, 10);
    Page<User> userSearch = userRepository.findByEmailContaining(email,friendListID, pageable3);

    model.addAttribute("allUsers", userSearch);
    model.addAttribute("keyWord", email);
    model.addAttribute("pagesUsers", new int[userSearch.getTotalPages()]);
    model.addAttribute("currentPageUsers", currentPageUsers);

    return "profile";

  }

  /**
   * Delete a Friend of connected user
   *
   * @param id - int
   * @return profile (html template)
   */
  @PostMapping(value = "/deletefriend")
  public String deleteFriend(int id) throws FriendException, UserAccountException {

    User connectedUser = iUserService.getConnectedUser();
    User friend = iUserService.getUserById(id);

    iUserService.deleteFriend(connectedUser, friend);

    return "redirect:profile";

  }

  /**
   * Add a Friend to connected user
   *
   * @param id - int
   * @return profile (html template)
   */
  @PostMapping(value = "/addfriend")
  public String addFriend(int id) throws FriendException, UserAccountException {

    User connectedUser = iUserService.getConnectedUser();
    User friend = iUserService.getUserById(id);

    iUserService.addFriendToUser(connectedUser, friend);

    return "redirect:profile";

  }

}
