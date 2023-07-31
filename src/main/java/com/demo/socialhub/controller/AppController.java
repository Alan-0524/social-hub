package com.demo.socialhub.controller;

import com.demo.socialhub.model.view.UserListView;
import com.demo.socialhub.model.view.UserProfileView;
import com.demo.socialhub.view.UserListViewService;
import com.demo.socialhub.view.UserProfileViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    private final UserListViewService userListViewService;

    private final UserProfileViewService userProfileViewService;

    @Autowired
    public AppController(UserListViewService userListViewService, UserProfileViewService userProfileViewService) {
        this.userListViewService = userListViewService;
        this.userProfileViewService = userProfileViewService;
    }

    @GetMapping("/users")
    public String fetchAllUsers(Model model) {
        UserListView userListView = userListViewService.constructView();
        model.addAttribute("userListView", userListView);
        return "userListViewTemplate";
    }

    @GetMapping("/user/{userId}")
    public String fetchUserProfile(Model model, @PathVariable("userId") int userId) {
        UserProfileView userProfileView = userProfileViewService.constructView(userId);
        model.addAttribute("userProfileView", userProfileView);
        return "userProfileViewTemplate";
    }
}
