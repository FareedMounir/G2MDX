package com.example.g2mdx.data.network.model;

public class User {

    private String profilePicture;
    private String name;

    public User(String profilePicture, String name) {
        this.profilePicture = profilePicture;
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getFirstName() {
        return name;
    }

}
