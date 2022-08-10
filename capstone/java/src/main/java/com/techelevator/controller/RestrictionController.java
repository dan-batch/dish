package com.techelevator.controller;

import com.techelevator.dao.RestrictionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Restriction;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/user")
public class RestrictionController {

    private RestrictionDao restrictionDao;

    public RestrictionController(RestrictionDao restrictionDao){
        this.restrictionDao = restrictionDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/restrictions/{restrictionId}")
    public boolean addRestrictionToUser(@PathVariable int restrictionId, Principal principal){
        return restrictionDao.addRestrictionToUser(restrictionId, principal);
    }

    @GetMapping(path = "/restrictions/{userId}")
    public List<Restriction> setRestrictionActive(@PathVariable int userId){
        return restrictionDao.setRestrictionActive(userId);
    }

}
