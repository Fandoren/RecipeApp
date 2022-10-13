package com.surmin.uaa.model;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    @Field
    @Indexed(unique = true, direction = IndexDirection.ASCENDING, name = "username_1")
    private String username;
    @Field
    @Indexed(unique = true, direction = IndexDirection.ASCENDING, name = "email_1")
    private String email;
    @Field
    private String password;
    @Field
    private String name;
    @Field
    private List<String> roles;
    @Field
    private byte[] avatarAsByteArray;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public byte[] getAvatarAsByteArray() {
        return avatarAsByteArray;
    }

    public void setAvatarAsByteArray(byte[] avatarAsByteArray) {
        this.avatarAsByteArray = avatarAsByteArray;
    }
}
