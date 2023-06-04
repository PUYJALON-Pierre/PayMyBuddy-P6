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

    // filtering allUsers by removing connectedUser and people already friends
    List<User> allUsers = iUserService.getUsersList();
    List<User> temp = new ArrayList<>();
    temp.addAll(allUsers);
    temp.removeIf(friendList::contains);
    temp.removeIf(user -> user.equals(connectedUser));

    // use sublist to create page from userList after filtering
    Pageable pageable2 = PageRequest.of(currentPageUsers, 5);
    int start2 = (int) pageable2.getOffset();
    int end1 = Math.min((start2 + pageable2.getPageSize()), temp.size());
    Page<User> allUsersFiltered = new PageImpl<User>(temp.subList(start2, end1), pageable2,
        temp.size());

    model.addAttribute("allUsers", allUsersFiltered.getContent());
    model.addAttribute("pagesUsers", new int[allUsersFiltered.getTotalPages()]);
    model.addAttribute("currentPageUsers", currentPageUsers);

    return "profile";

  }

//todo
  @GetMapping(value = "/userSearch")
  public String searchFriend(Model model,
      @RequestParam(name = "page", defaultValue = "0") int currentPage,
      @RequestParam(name = "keyWord", defaultValue = "") String email) throws UserAccountException {
    String userSearch = "Not found";
    try {
      User user = iUserService.getUserByAppAcount(iUserAccountService.findUserByEmail(email));
      userSearch = user.getUserAccount().getEmail();
    } catch (Exception e) {

      return "profile";
    }

    model.addAttribute("userSearch", userSearch);
    model.addAttribute("keyWord", email);

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
