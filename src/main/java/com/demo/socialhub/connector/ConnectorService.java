package com.demo.socialhub.connector;

public interface ConnectorService {
    <T> T get(String url, Class<T> responseType, Object... uriVariables);

    <T> T post(String url, Object request, Class<T> responseType, Object... uriVariables);

    void put(String url, Object request, Object... uriVariables);

    void delete(String url, Object... uriVariables);

    <T> T patch(String url, Object request, Class<T> responseType, Object... uriVariables);
}
