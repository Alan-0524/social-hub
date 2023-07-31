package com.demo.socialhub.service;

import com.demo.socialhub.model.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> fetchPostsByUserId(int userId);
}
