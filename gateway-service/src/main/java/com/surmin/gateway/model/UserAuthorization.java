package com.surmin.gateway.model;

public class UserAuthorization {

    private final String id;
    private final String username;
    private final String token;

    public UserAuthorization() {
        this.id = null;
        this.username = null;
        this.token = null;
    }

    public UserAuthorization(String id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

}
