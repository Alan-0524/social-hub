package com.demo.socialhub.service;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.Post;
import com.demo.socialhub.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private ConnectorService connectorService;

    @Mock
    private ConnectorConfig connectorConfig;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchPostsByUserId() {
        // Mock the connectorConfig
        when(connectorConfig.getBaseUrl()).thenReturn("https://example.com/api/");
        when(connectorConfig.getPostEndpoint()).thenReturn("posts");

        // Mock the response
        int userId = 1;
        Post post_1 = new Post();
        post_1.setId(1);
        post_1.setUserId(userId);
        post_1.setTitle("Post 1");
        Post post_2 = new Post();
        post_2.setId(2);
        post_2.setUserId(userId);
        post_2.setTitle("Post 2");
        Post[] postsArray = new Post[]{post_1, post_2};
        when(connectorService.get("https://example.com/api/posts?userId=1", Post[].class)).thenReturn(postsArray);

        // Test the service method
        List<Post> posts = postService.fetchPostsByUserId(userId);

        // Verify the interactions and assertions
        verify(connectorConfig, times(1)).getBaseUrl();
        verify(connectorConfig, times(1)).getPostEndpoint();
        verify(connectorService, times(1)).get("https://example.com/api/posts?userId=1", Post[].class);

        assertEquals(2, posts.size());
        assertEquals(userId, posts.get(0).getUserId());
        assertEquals("Post 1", posts.get(0).getTitle());
        assertEquals(userId, posts.get(1).getUserId());
        assertEquals("Post 2", posts.get(1).getTitle());
    }
}
