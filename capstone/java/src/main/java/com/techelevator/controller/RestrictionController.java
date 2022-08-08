package com.techelevator.controller;

import com.techelevator.dao.RestrictionDao;
import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RestrictionController {

    private RestrictionDao restrictionDao;

    private RestrictionController(RestrictionDao restrictionDao){
        restrictionDao = this.restrictionDao;
    }

    @PutMapping(path = "/{id}/restrictions")
    public boolean addRestrictionToUser(@PathVariable int id){
        return restrictionDao.addRestrictionToUser(id);
    }


}
