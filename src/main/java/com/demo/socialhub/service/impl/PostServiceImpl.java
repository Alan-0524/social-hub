package com.demo.socialhub.service.impl;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.Post;
import com.demo.socialhub.service.PostService;
import com.demo.socialhub.utils.ArrayUtilsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private final ConnectorService connectorService;
    private final ConnectorConfig connectorConfig;

    @Autowired
    public PostServiceImpl(ConnectorService connectorService, ConnectorConfig connectorConfig) {
        this.connectorService = connectorService;
        this.connectorConfig = connectorConfig;
    }

    @Override
    public List<Post> fetchPostsByUserId(int userId) {
        String postEndpoint = connectorConfig.getPostEndpoint();
        String url = connectorConfig.getBaseUrl().concat(postEndpoint).concat("?userId=").concat(String.valueOf(userId));
        LOGGER.info("Start fetching posts by calling url: {}", url);
        Post[] postsArray = connectorService.get(url, Post[].class);
        LOGGER.info("Fetching posts finished");
        return ArrayUtilsWrapper.asListOrEmpty(postsArray);
    }
}
