package com.techelevator.controller;

import com.techelevator.dao.RestrictionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Restriction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class RestrictionController {

    private RestrictionDao restrictionDao;
    private UserDao userDao;

    public RestrictionController(RestrictionDao restrictionDao, UserDao userDao){
        this.restrictionDao = restrictionDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "/{userId}/restrictions")
    public boolean addRestrictionToUser(@PathVariable int userId, @RequestBody List<Integer> restrictionIdList){
        return restrictionDao.updateUserRestrictions(userId, restrictionIdList);
    }

    @GetMapping(path = "/{userId}/restrictions")
    public List<Restriction> setRestrictionActive(@PathVariable int userId){
        return restrictionDao.getUserRestrictions(userId);
    }

}
