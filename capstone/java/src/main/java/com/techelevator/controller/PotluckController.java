package com.techelevator.controller;

import com.techelevator.dao.PotluckDao;
import com.techelevator.model.AddCatToPluckDTO;
import com.techelevator.model.Category;
import com.techelevator.model.Dish;
import com.techelevator.model.Potluck;
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


    public PotluckController(PotluckDao potluckDao) {
        this.potluckDao = potluckDao;
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
    public Integer createPluck(@Valid @RequestBody Potluck potluck) {
        return potluckDao.createPluck(potluck.getPluckName(),
                potluck.getPluckTime(), potluck.getPluckPlace(),
                potluck.getPluckDescription(), potluck.getPluckImageURL());
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

    @PostMapping(path = "/addDish")
    public boolean addDish(@Valid @RequestBody Dish dish) {
        return potluckDao.addDish(dish.getDishId(), dish.getDishPluckId(), dish.getDishCatId(), dish.getDishUserId(), dish.getDishName());
    }

    @PostMapping(path = "/addCat")
    public boolean addCat(@RequestBody AddCatToPluckDTO newCat){
        return potluckDao.addCat(newCat.getPluckId(), newCat.getCatId(), newCat.getLimit());
    }

}
