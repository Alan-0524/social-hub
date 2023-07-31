package com.demo.socialhub.model.view;

import com.demo.socialhub.model.entity.User;

import java.util.List;

public class UserProfileView extends View {
    private User user;

    private List<PostView> postViews;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostView> getPostViews() {
        return postViews;
    }

    public void setPostViews(List<PostView> postViews) {
        this.postViews = postViews;
    }
}
