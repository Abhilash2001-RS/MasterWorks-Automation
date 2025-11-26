package com.aurigo.masterworks.testframework.utilities.models;

import java.util.List;

public class User {
    public String username;
    public String password;
    public List<String> roles;
    public boolean isBusy;

    @Override
    public String toString() {
        return "User: {" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", isBusy=" + isBusy +
                '}';
    }
}
