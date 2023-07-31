package com.demo.socialhub.view;

import com.demo.socialhub.model.entity.Comment;
import com.demo.socialhub.model.entity.Post;
import com.demo.socialhub.model.entity.User;
import com.demo.socialhub.model.view.PostView;
import com.demo.socialhub.model.view.UserProfileView;
import com.demo.socialhub.service.CommentService;
import com.demo.socialhub.service.PostService;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.view.impl.UserProfileViewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserProfileViewServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private UserProfileViewServiceImpl userProfileViewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructView() {
        int userId = 1;

        // Mock the UserService response
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setName("User 1");
        when(userService.fetchUserByUserId(userId)).thenReturn(mockUser);

        // Mock the PostService response
        Post mockPost1 = new Post();
        mockPost1.setId(1);
        mockPost1.setUserId(userId);
        mockPost1.setTitle("Post 1");
        Post mockPost2 = new Post();
        mockPost2.setId(2);
        mockPost2.setUserId(userId);
        mockPost2.setTitle("Post 2");
        List<Post> mockPosts = Arrays.asList(mockPost1, mockPost2);
        when(postService.fetchPostsByUserId(userId)).thenReturn(mockPosts);

        // Mock the CommentService response
        Comment mockComment1 = new Comment();
        mockComment1.setId(1);
        mockComment1.setPostId(1);
        mockComment1.setName("Comment 1");
        Comment mockComment2 = new Comment();
        mockComment2.setId(2);
        mockComment2.setPostId(1);
        mockComment2.setName("Comment 2");
        List<Comment> mockCommentsPost1 = Arrays.asList(mockComment1, mockComment2);
        when(commentService.fetchCommentsByPostId(1)).thenReturn(mockCommentsPost1);

        Comment mockComment3 = new Comment();
        mockComment3.setId(3);
        mockComment3.setPostId(2);
        mockComment3.setName("Comment 3");
        List<Comment> mockCommentsPost2 = Collections.singletonList(mockComment3);
        when(commentService.fetchCommentsByPostId(2)).thenReturn(mockCommentsPost2);

        // Test the service method
        UserProfileView userProfileView = userProfileViewService.constructView(userId);

        // Verify the interactions and assertions
        verify(userService, times(1)).fetchUserByUserId(userId);
        verify(postService, times(1)).fetchPostsByUserId(userId);
        verify(commentService, times(1)).fetchCommentsByPostId(1);
        verify(commentService, times(1)).fetchCommentsByPostId(2);

        assertEquals(userId, userProfileView.getUser().getId());
        assertEquals("User 1", userProfileView.getUser().getName());

        List<PostView> postViews = userProfileView.getPostViews();
        assertEquals(2, postViews.size());

        PostView postView1 = postViews.get(0);
        assertEquals(1, postView1.getPost().getId());
        assertEquals("Post 1", postView1.getPost().getTitle());
        assertEquals(2, postView1.getComments().size());
        assertEquals("Comment 1", postView1.getComments().get(0).getName());
        assertEquals("Comment 2", postView1.getComments().get(1).getName());

        PostView postView2 = postViews.get(1);
        assertEquals(2, postView2.getPost().getId());
        assertEquals("Post 2", postView2.getPost().getTitle());
        assertEquals(1, postView2.getComments().size());
        assertEquals("Comment 3", postView2.getComments().get(0).getName());
    }
}
