package com.demo.socialhub.connector.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "connector.endpoint")
public class ConnectorConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorConfig.class);
    private String baseUrl;

    private String userEndpoint;

    private String postEndpoint;

    private String commentEndpoint;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUserEndpoint() {
        return userEndpoint;
    }

    public void setUserEndpoint(String userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    public String getPostEndpoint() {
        return postEndpoint;
    }

    public void setPostEndpoint(String postEndpoint) {
        this.postEndpoint = postEndpoint;
    }

    public String getCommentEndpoint() {
        return commentEndpoint;
    }

    public void setCommentEndpoint(String commentEndpoint) {
        this.commentEndpoint = commentEndpoint;
    }

    @PostConstruct
    private void afterInit() {
        LOGGER.info(String.format("connector configuration: baseUrl = %s, userEndpoint = %s, postEndpoint = %s, commentEndpoint = %s", getBaseUrl(), getUserEndpoint(), getPostEndpoint(), getCommentEndpoint()));
    }
}
