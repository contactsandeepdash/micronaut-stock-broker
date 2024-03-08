package com.dash.service;

import jakarta.inject.Singleton;

@Singleton
public class WatchListService {

    public String welcomeFromService() {
        return "Welcome to Micronaut from service!";
    }

    public String helloWorldFromService() {
        return "Hello from service!";
    }
}
