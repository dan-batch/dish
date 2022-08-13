package com.techelevator.controller;

import com.techelevator.dao.PotluckDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Potluck;
import com.techelevator.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/pluck")
@CrossOrigin
public class PotluckController {
    private PotluckDao potluckDao;
    private UserDao userDao;

    public PotluckController(PotluckDao potluckDao, UserDao userDao){
        this.potluckDao = potluckDao;
        this.userDao = userDao;
    }

    @GetMapping(path = "")
    public List<Potluck> getAllPlucks() {return potluckDao.getAllPlucks();}

    @GetMapping(path = "/{pluckId}")
    public Potluck getPluckById(@PathVariable int pluckId){return potluckDao.getPluckById(pluckId);}

    @GetMapping(path = "/{userId}")
    public List<Potluck> getAllPlucksByUser(@PathVariable int userId){return potluckDao.getAllPlucksByUser(userId);}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public boolean createPluck(String pluckName, LocalDateTime pluckTime, String pluckPlace)
    {return potluckDao.createPluck(pluckName, pluckTime, pluckPlace);}


}
