package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.UserDTO;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private UserDao userDao;

    private UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(path = "")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userDao.getUserById(userId);
    }

    @PutMapping(path = "/{userId}") //Does not include means to update first name, last name, role, or password.
    public User updateUserProfile(@PathVariable int userId, @RequestBody UserDTO updatedUser) {
        return userDao.updateUserProfile(userId, updatedUser);
    }

    @GetMapping(path = "/email")
    public User findByEmail(@RequestParam String email) {
        return userDao.findByEmail(email);
    }

//    @GetMapping(path = "/id/{email}")
//    public int findIdByEmail(@RequestParam String email){
//        return userDao.findIdByEmail(email);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public boolean create(String email, String password, String role) {
        return userDao.create(email, password, role);

    }
}
