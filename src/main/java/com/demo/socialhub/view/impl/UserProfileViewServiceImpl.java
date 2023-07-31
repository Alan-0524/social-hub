package com.demo.socialhub.view.impl;

import com.demo.socialhub.model.view.PostView;
import com.demo.socialhub.model.view.UserProfileView;
import com.demo.socialhub.service.CommentService;
import com.demo.socialhub.service.PostService;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.view.UserProfileViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileViewServiceImpl implements UserProfileViewService {

    private final UserService userService;

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public UserProfileViewServiceImpl(UserService userService, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @Override
    public UserProfileView constructView(int userId) {
        UserProfileView userProfileView = new UserProfileView();
        List<PostView> postViews = new ArrayList<>();
        postService.fetchPostsByUserId(userId).forEach(post -> {
            postViews.add(new PostView(post, commentService.fetchCommentsByPostId(post.getId())));
        });
        userProfileView.setUser(userService.fetchUserByUserId(userId));
        userProfileView.setPostViews(postViews);
        return userProfileView;
    }
}
