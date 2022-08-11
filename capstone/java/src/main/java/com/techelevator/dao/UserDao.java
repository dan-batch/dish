package com.techelevator.dao;

import com.techelevator.model.UpdateUserProfileDTO;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    User updateUserProfile(int userId, UpdateUserProfileDTO updatedUser);

    List<User> findAll();

    User getUserById(int userId);

    User findByEmail(String email);

//    int findIdByEmail(String email);

    boolean create(String email, String password, String role);

    int getIdByUsername(String username);
}
