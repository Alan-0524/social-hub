package com.demo.socialhub.model.view;

import com.demo.socialhub.model.entity.User;

import java.util.List;

public class UserListView extends View {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
