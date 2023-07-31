package com.demo.socialhub.service;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.Comment;
import com.demo.socialhub.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private ConnectorService connectorService;

    @Mock
    private ConnectorConfig connectorConfig;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchCommentsByPostId() {
        // Mock the connectorConfig
        when(connectorConfig.getBaseUrl()).thenReturn("https://example.com/api/");
        when(connectorConfig.getCommentEndpoint()).thenReturn("comments");

        // Mock the response
        int postId = 1;
        Comment comment_1 = new Comment();
        comment_1.setId(1);
        comment_1.setPostId(postId);
        comment_1.setName("test1");
        comment_1.setBody("test1");
        Comment comment_2 = new Comment();
        comment_2.setId(2);
        comment_2.setPostId(postId);
        comment_2.setName("test2");
        comment_2.setBody("test2");
        Comment[] commentsArray = new Comment[]{comment_1, comment_2};
        when(connectorService.get("https://example.com/api/comments?postId=1", Comment[].class)).thenReturn(commentsArray);

        // Test the service method
        List<Comment> comments = commentService.fetchCommentsByPostId(postId);

        // Verify the interactions and assertions
        verify(connectorConfig, times(1)).getBaseUrl();
        verify(connectorConfig, times(1)).getCommentEndpoint();
        verify(connectorService, times(1)).get("https://example.com/api/comments?postId=1", Comment[].class);

        assertEquals(2, comments.size());
        assertEquals(postId, comments.get(0).getPostId());
        assertEquals("test1", comments.get(0).getName());
        assertEquals("test1", comments.get(0).getBody());
        assertEquals(postId, comments.get(1).getPostId());
        assertEquals("test2", comments.get(1).getName());
        assertEquals("test2", comments.get(1).getBody());
    }
}
