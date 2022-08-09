package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByEmail(String email);

//    int findIdByEmail(String email);

    boolean create(String email, String password);
}
