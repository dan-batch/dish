package com.techelevator.controller;

import com.techelevator.dao.RestrictionDao;
import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class RestrictionController {

    private RestrictionDao restrictionDao;

    private RestrictionController(RestrictionDao restrictionDao){
        this.restrictionDao = restrictionDao;
    }

    @PostMapping(path = "/restrictions")
    public boolean addRestrictionToUser(int restrictionId, Principal principal){
        return restrictionDao.addRestrictionToUser(restrictionId, principal);
    }

    @GetMapping(path = "/restrictions/{userId}")
    public boolean setRestrictionActive(@PathVariable int userId){
        return restrictionDao.setRestrictionActive(userId);
    }

}
