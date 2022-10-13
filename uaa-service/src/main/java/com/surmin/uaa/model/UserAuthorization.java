package com.surmin.uaa.model;

public class UserAuthorization {

    private final String id;
    private final String username;
    private final String token;

    public UserAuthorization(User user, String token) {
        this.id = user.getId();
        this.username = user.getUsername();
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
