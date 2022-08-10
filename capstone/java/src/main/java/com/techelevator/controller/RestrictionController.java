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
        restrictionDao = this.restrictionDao;
    }

    @PostMapping(path = "/restrictions")
    public boolean addRestrictionToUser(int restrictionId, Principal principal){
        return restrictionDao.addRestrictionToUser(restrictionId, principal);
    }


}
