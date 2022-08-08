package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserDao userDao;

    private UserController(UserDao userDao){
        userDao = this.userDao;
    }

    @GetMapping(path = "")
    public List<User> findAll() { return userDao.findAll(); }

    @GetMapping(path = "/{userId}")
    public User getUserById (@PathVariable int userId){
        return userDao.getUserById(userId);
    }

    @GetMapping(path = "/{email}/id")
    public User findByEmail(@PathVariable String email){
        return userDao.findByEmail(email);
    }

    @GetMapping(path = "/{email}")
    public int findIdByEmail(@PathVariable String email){
        return userDao.findIdByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public boolean create(String email, String password){
        return userDao.create(email, password);

    }
}
