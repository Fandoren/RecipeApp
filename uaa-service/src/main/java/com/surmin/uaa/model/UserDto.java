package com.surmin.uaa.model;

import java.util.List;
import javax.management.relation.Role;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

    private static final String EMAIL_REGEX =
            "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|"
                    + "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\"
                    + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)"
                    + "+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
                    + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c"
                    + "\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String USER_NAME_REGEX = "^[\\w+-]{2,55}$";

    @Pattern(regexp = "[a-fA-F\\d]{24}", message = "entityId should match regex [a-fA-F\\d]{24}")
    private String entityId;
    private String username;

    @NotNull
    @Pattern(regexp = EMAIL_REGEX, message = "email is not correct")
    private String email;

    @NotNull(message = "name can't be undefined")
    @Pattern(regexp = USER_NAME_REGEX, message = "Name can't be less than 2 or more than 55 letters")
    private String name;

    @NotNull
    private String password;
    
    private List<String> roles;

    public UserDto() {
    }

    public UserDto(User user) {
        entityId = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        roles = user.getRoles();
    }

    public User toUser() {
        User user = new User();
        user.setId(entityId);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setRoles(roles);
        return user;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
