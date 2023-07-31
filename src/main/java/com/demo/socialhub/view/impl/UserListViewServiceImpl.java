package com.demo.socialhub.view.impl;

import com.demo.socialhub.model.view.UserListView;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.view.UserListViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListViewServiceImpl implements UserListViewService {

    private final UserService userService;

    @Autowired
    public UserListViewServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserListView constructView() {
        UserListView userListView = new UserListView();
        userListView.setUsers(userService.fetchAllUsers());
        return userListView;
    }
}
