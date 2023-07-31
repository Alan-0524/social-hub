package com.demo.socialhub.model.view;

import com.demo.socialhub.model.entity.Comment;
import com.demo.socialhub.model.entity.Post;

import java.util.List;

public class PostView extends View {
    private Post post;

    private List<Comment> comments;

    public PostView() {
    }

    public PostView(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
