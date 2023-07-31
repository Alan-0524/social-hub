package com.demo.socialhub.service;

import com.demo.socialhub.connector.ConnectorService;
import com.demo.socialhub.connector.config.ConnectorConfig;
import com.demo.socialhub.model.entity.User;
import com.demo.socialhub.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private ConnectorService connectorService;

    @Mock
    private ConnectorConfig connectorConfig;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchAllUsers() {
        // Mock the connectorConfig
        when(connectorConfig.getBaseUrl()).thenReturn("https://example.com/api/");
        when(connectorConfig.getUserEndpoint()).thenReturn("users");

        User user_1 = new User();
        user_1.setId(1);
        user_1.setName("John");
        User user_2 = new User();
        user_2.setId(2);
        user_2.setName("Alice");
        when(connectorService.get("https://example.com/api/users/1", User.class)).thenReturn(user_1);
        when(connectorService.get("https://example.com/api/users/2", User.class)).thenReturn(user_2);
        // Mock the response
        User[] usersArray = new User[]{user_1, user_2};
        when(connectorService.get("https://example.com/api/users", User[].class)).thenReturn(usersArray);

        // Test the service method
        List<User> users = userService.fetchAllUsers();

        // Verify the interactions and assertions
        verify(connectorConfig, times(1)).getBaseUrl();
        verify(connectorConfig, times(1)).getUserEndpoint();
        verify(connectorService, times(1)).get("https://example.com/api/users", User[].class);

        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Alice", users.get(1).getName());
    }

    @Test
    void testFetchUserByUserId() {
        // Mock the connectorConfig
        when(connectorConfig.getBaseUrl()).thenReturn("https://example.com/api/");
        when(connectorConfig.getUserEndpoint()).thenReturn("users/");

        // Mock the response
        int userId = 1;
        User user = new User();
        user.setId(1);
        user.setName("John");
        when(connectorService.get("https://example.com/api/users/1", User.class)).thenReturn(user);

        // Test the service method
        User result = userService.fetchUserByUserId(userId);

        // Verify the interactions and assertions
        verify(connectorConfig, times(1)).getBaseUrl();
        verify(connectorConfig, times(1)).getUserEndpoint();
        verify(connectorService, times(1)).get("https://example.com/api/users/1", User.class);

        assertEquals(userId, result.getId());
        assertEquals("John", result.getName());
    }
}
