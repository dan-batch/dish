package com.techelevator.model;

import java.util.List;

/**
 * DTO for updating a user's email and/or image URL.
 */
public class UpdateUserProfileDTO {

    private List<Object> authorities;
    private int userId;
    private String email;
    private String imageURL;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "UpdateUserProfileDTO{" +
                "email='" + email + '\'' +
                ", imageURL='" + imageURL +
                '}';
    }
}
