package com.demo.socialhub.controller;

import com.demo.socialhub.model.entity.User;
import com.demo.socialhub.model.view.UserListView;
import com.demo.socialhub.model.view.UserProfileView;
import com.demo.socialhub.view.UserListViewService;
import com.demo.socialhub.view.UserProfileViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AppControllerTest {

    @Mock
    private UserListViewService userListViewService;

    @Mock
    private UserProfileViewService userProfileViewService;

    @Mock
    private Model model;

    @InjectMocks
    private AppController appController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchAllUsers() {
        // Mock the UserListViewService response
        User user_1 = new User();
        user_1.setId(1);
        user_1.setName("User 1");
        User user_2 = new User();
        user_2.setId(2);
        user_2.setName("User 2");
        List<User> mockUsers = Arrays.asList(user_1, user_2);
        UserListView mockUserListView = new UserListView();
        mockUserListView.setUsers(mockUsers);
        when(userListViewService.constructView()).thenReturn(mockUserListView);

        // Test the controller method
        String viewName = appController.fetchAllUsers(model);

        // Verify the interactions and assertions
        verify(userListViewService, times(1)).constructView();
        verify(model, times(1)).addAttribute("userListView", mockUserListView);
        assertEquals("userListViewTemplate", viewName);
    }

    @Test
    void testFetchUserProfile() {
        int userId = 1;

        // Mock the UserProfileViewService response
        User user_1 = new User();
        user_1.setId(1);
        user_1.setName("User 1");
        UserProfileView mockUserProfileView = new UserProfileView();
        mockUserProfileView.setUser(user_1);

        when(userProfileViewService.constructView(userId)).thenReturn(mockUserProfileView);

        // Test the controller method
        String viewName = appController.fetchUserProfile(model, userId);

        // Verify the interactions and assertions
        verify(userProfileViewService, times(1)).constructView(userId);
        verify(model, times(1)).addAttribute(anyString(), any(UserProfileView.class));
        assertEquals("userProfileViewTemplate", viewName);
    }
}
