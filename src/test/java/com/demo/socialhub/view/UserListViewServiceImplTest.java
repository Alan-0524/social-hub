package com.demo.socialhub.view;

import com.demo.socialhub.model.entity.User;
import com.demo.socialhub.model.view.UserListView;
import com.demo.socialhub.service.UserService;
import com.demo.socialhub.view.impl.UserListViewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserListViewServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserListViewServiceImpl userListViewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructView() {
        User user_1 = new User();
        user_1.setId(1);
        user_1.setName("John");
        User user_2 = new User();
        user_2.setId(2);
        user_2.setName("Alice");
        // Mock the UserService response
        List<User> mockUsers = Arrays.asList(user_1, user_2);
        when(userService.fetchAllUsers()).thenReturn(mockUsers);

        // Test the service method
        UserListView userListView = userListViewService.constructView();

        // Verify the interactions and assertions
        verify(userService, times(1)).fetchAllUsers();

        List<User> users = userListView.getUsers();
        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals("John", users.get(0).getName());
        assertEquals(2, users.get(1).getId());
        assertEquals("Alice", users.get(1).getName());
    }
}
