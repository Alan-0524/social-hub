package com.demo.socialhub.connector.impl;

import com.demo.socialhub.connector.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectorServiceImpl implements ConnectorService {

    private final RestTemplate restTemplate;

    @Autowired
    public ConnectorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T get(String url, Class<T> responseType, Object... uriVariables) {
        return restTemplate.getForObject(url, responseType);
    }

    @Override
    public <T> T post(String url, Object request, Class<T> responseType, Object... uriVariables) {
        return restTemplate.postForObject(url, request, responseType);
    }

    @Override
    public void put(String url, Object request, Object... uriVariables) {
        restTemplate.put(url, request, uriVariables);
    }

    @Override
    public void delete(String url, Object... uriVariables) {
        restTemplate.delete(url, uriVariables);
    }

    @Override
    public <T> T patch(String url, Object request, Class<T> responseType, Object... uriVariables) {
        return restTemplate.patchForObject(url, request, responseType);
    }
}
