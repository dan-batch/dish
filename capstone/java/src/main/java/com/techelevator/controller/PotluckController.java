package com.techelevator.controller;

import com.techelevator.dao.PotluckDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Potluck;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    public PotluckController(PotluckDao potluckDao, UserDao userDao) {
        this.potluckDao = potluckDao;
        this.userDao = userDao;
    }

    @GetMapping(path = "")
    public List<Potluck> getAllPlucks() {
        return potluckDao.getAllPlucks();
    }

    @GetMapping(path = "/pluck{pluckId}")
    public Potluck getPluckById(@PathVariable int pluckId) {
        return potluckDao.getPluckById(pluckId);
    }

    @GetMapping(path = "/user{userId}")
    public List<Potluck> getAllPlucksByUser(@PathVariable int userId) {
        return potluckDao.getAllPlucksByUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public boolean createPluck(@Valid @RequestBody Potluck potluck) {
        return potluckDao.createPluck(potluck.getPluckName(), potluck.getPluckTime(), potluck.getPluckPlace());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/update")
    public boolean updatePluck(@Valid @RequestBody Potluck potluck) {
        return potluckDao.updatePluck(potluck.getPluckId(), potluck.getPluckName(), potluck.getPluckDescription(), potluck.getPluckTime(), potluck.getPluckPlace());
    }

    @GetMapping(path = "/name_{pluckName}")
    public Potluck getPluckByName(@PathVariable String pluckName) {
        return potluckDao.getPluckByName(pluckName);
    }


}
