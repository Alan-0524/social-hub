package com.demo.socialhub;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.controller.AppController;
import com.demo.socialhub.service.CommentService;
import com.demo.socialhub.service.PostService;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.view.UserListViewService;
import com.demo.socialhub.view.UserProfileViewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SocialHubApplicationTests {

    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserListViewService userListViewService;
    @Autowired
    private UserProfileViewService userProfileViewService;
    @Autowired
    private AppController appController;

    @Test
    void contextLoads() {
        assertThat(appController).isNotNull();
        assertThat(connectorService).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(commentService).isNotNull();
        assertThat(postService).isNotNull();
        assertThat(userListViewService).isNotNull();
        assertThat(userProfileViewService).isNotNull();
    }
}
