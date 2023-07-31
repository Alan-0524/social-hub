package com.demo.socialhub.service.impl;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.User;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.utils.ArrayUtilsWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ConnectorService connectorService;
    private final ConnectorConfig connectorConfig;

    @Autowired
    public UserServiceImpl(ConnectorService connectorService, ConnectorConfig connectorConfig) {
        this.connectorService = connectorService;
        this.connectorConfig = connectorConfig;
    }

    @Override
    public List<User> fetchAllUsers() {
        String userEndpoint = connectorConfig.getUserEndpoint();
        String url = connectorConfig.getBaseUrl().concat(userEndpoint);
        LOGGER.info("Start fetching all users by calling url: {}", url);
        User[] usersArray = connectorService.get(url, User[].class);
        LOGGER.info("Fetching users finished");
        return ArrayUtilsWrapper.asListOrEmpty(usersArray);
    }

    @Override
    public User fetchUserByUserId(int userId) {
        String userEndpoint = connectorConfig.getUserEndpoint().concat(String.valueOf(userId));
        String url = connectorConfig.getBaseUrl().concat(userEndpoint);
        LOGGER.info("Start fetching a user by calling url: {}", url);
        User user = connectorService.get(url, User.class);
        LOGGER.info("Fetching the user finished");
        return user;
    }
}
