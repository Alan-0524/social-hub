package com.demo.socialhub.service;

import com.demo.socialhub.connector.impl.ConnectorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

class ConnectorServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConnectorServiceImpl connectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        String url = "https://example.com/api/users/1";
        Object responseObject = new Object(); // Replace Object with the expected response type
        doReturn(responseObject).when(restTemplate).getForObject(url, Object.class);
        Object result = connectorService.get(url, Object.class);
        assertEquals(responseObject, result);
    }

    @Test
    void testGetFailure() {
        String url = "https://example.com/api/users/1";
        doThrow(RestClientException.class).when(restTemplate).getForObject(url, Object.class);
        assertThrows(RestClientException.class, () -> connectorService.get(url, Object.class));
    }
}

