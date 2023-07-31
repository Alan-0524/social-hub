package com.demo.socialhub.service;

import com.demo.socialhub.model.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> fetchCommentsByPostId(int postId);
}
