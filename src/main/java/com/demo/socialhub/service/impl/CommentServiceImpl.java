package com.demo.socialhub.service.impl;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.Comment;
import com.demo.socialhub.service.CommentService;
import com.demo.socialhub.utils.ArrayUtilsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final ConnectorService connectorService;
    private final ConnectorConfig connectorConfig;

    @Autowired
    public CommentServiceImpl(ConnectorService connectorService, ConnectorConfig connectorConfig) {
        this.connectorService = connectorService;
        this.connectorConfig = connectorConfig;
    }

    @Override
    public List<Comment> fetchCommentsByPostId(int postId) {
        String commentEndpoint = connectorConfig.getCommentEndpoint();
        String url = connectorConfig.getBaseUrl().concat(commentEndpoint).concat("?postId=").concat(String.valueOf(postId));
        Comment[] commentsArray = connectorService.get(url, Comment[].class);
        return ArrayUtilsWrapper.asListOrEmpty(commentsArray);
    }
}
