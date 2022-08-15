package com.techelevator.controller;

import com.techelevator.dao.CategoryDao;
import com.techelevator.dao.DishDao;
import com.techelevator.model.Category;
import com.techelevator.model.Dish;
import com.techelevator.model.Potluck;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/dish")
@CrossOrigin


public class DishController {
    private DishDao dishDao;

    private DishController(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @GetMapping(path = "") //todo: test with values in DB
    public List<Dish> getAllDishes() {
        return dishDao.getAllDishes();
    }

    @PostMapping(path = "/create") //todo: test with values in DB
    public boolean createDish(@RequestBody @Valid Dish dish) {
        return dishDao.createDish(dish.getDishPluckId(), dish.getDishCatId(), dish.getDishUserId(), dish.getDishName());
    }

    @GetMapping(path = "/dish{dishId}") //todo: test with values in DB
    public Dish getDishById(@PathVariable int dishId) {
        return dishDao.getDishById(dishId);
    }

    @GetMapping(path = "/user{userId}pluck{pluckId}") //todo: test with values in DB
    public List<Dish> getDishesByUserAndPluck(@PathVariable int userId, @PathVariable int pluckId) {
        return dishDao.getDishesByUserAndPluck(userId, pluckId);
    }

    @GetMapping(path = "/pluck{pluckId}") //todo: test with values in DB
    public List<Dish> getDishesByPluckId(@PathVariable int pluckId) {
        return dishDao.getDishesByPluckId(pluckId);
    }

    @GetMapping(path = "/cat{catId}pluck{pluckId}") //todo: test with values in DB
    public List<Dish> getDishesByCatAndPluck(@PathVariable int catId, @PathVariable int pluckId) {
        return dishDao.getDishesByCatAndPluck(catId, pluckId);
    }

    @GetMapping(path = "/cat_of_dish{dishId}") //todo: test with values in DB
    public Category getCatByDishId(@PathVariable int dishId) {
        return dishDao.getCategoryByDishId(dishId);
    }

    @GetMapping(path = "/pluck_of_dish{dishId}") //todo: test with values in DB
    public Potluck getPluckByDishId(@PathVariable int dishId) {
        return dishDao.getPluckByDishId(dishId);
    }

    @PostMapping(path = "/addRestriction") //todo: test with values in DB
    public boolean addRestriction(int dishId, int restrictionId) {
        return dishDao.addRestriction(dishId, restrictionId);
    }


}
