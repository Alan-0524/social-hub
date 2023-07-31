package com.demo.socialhub.service;

import com.demo.socialhub.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();

    User fetchUserByUserId(int userId);
}
