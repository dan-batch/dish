package com.techelevator.controller;

import com.techelevator.dao.RestrictionDao;
import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RestrictionController {

    private RestrictionDao restrictionDao;

    private RestrictionController(RestrictionDao restrictionDao){
        this.restrictionDao = restrictionDao;
    }

    @PostMapping(path = "/restrictions")
    public boolean addRestrictionToUser(int userId, int restrictionId){
        return restrictionDao.addRestrictionToUser(userId, restrictionId);
    }


}
