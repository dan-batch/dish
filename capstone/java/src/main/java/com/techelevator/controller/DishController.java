package com.techelevator.controller;

import com.techelevator.dao.DishDao;
import com.techelevator.model.AddRestrictionToDishDTO;
import com.techelevator.model.Category;
import com.techelevator.model.Dish;
import com.techelevator.model.Potluck;
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

    @GetMapping(path = "")
    public List<Dish> getAllDishes() {
        return dishDao.getAllDishes();
    }

    @PostMapping(path = "/create")
    public boolean createDish(@RequestBody @Valid Dish dish) {
        return dishDao.createDish(dish.getDishPluckId(), dish.getDishCatId(), dish.getDishUserId(), dish.getDishName());
    }

    @PutMapping(path="/update/{dishId}")
    public boolean updateDish(@RequestBody @Valid Dish dish, @PathVariable int dishId){
        return dishDao.updateDish(dishId, dish.getDishDescription(), dish.getDishName(), dish.getServings());
    }

    @GetMapping(path = "/dish{dishId}")
    public Dish getDishById(@PathVariable int dishId) {
        return dishDao.getDishById(dishId);
    }

    @GetMapping(path = "/user{userId}/pluck{pluckId}")
    public List<Dish> getDishesByUserAndPluck(@PathVariable int userId, @PathVariable int pluckId) {
        return dishDao.getDishesByUserAndPluck(userId, pluckId);
    }

    @GetMapping(path = "/pluck{pluckId}")
    public List<Dish> getDishesByPluckId(@PathVariable int pluckId) {
        return dishDao.getDishesByPluckId(pluckId);
    }

    @GetMapping(path = "/cat{catId}/pluck{pluckId}")
    public List<Dish> getDishesByCatAndPluck(@PathVariable int catId, @PathVariable int pluckId) {
        return dishDao.getDishesByCatAndPluck(catId, pluckId);
    }

    @GetMapping(path = "/cat_of_dish{dishId}")
    public Category getCatByDishId(@PathVariable int dishId) {
        return dishDao.getCategoryByDishId(dishId);
    }

    @GetMapping(path = "/pluck_of_dish{dishId}")
    public Potluck getPluckByDishId(@PathVariable int dishId) {
        return dishDao.getPluckByDishId(dishId);
    }

    @PostMapping(path = "/addRestriction")
    public boolean addRestriction(@RequestBody AddRestrictionToDishDTO newCat) {
        return dishDao.addRestriction(newCat.getDishId(), newCat.getRestrictionId());
    }

    @DeleteMapping(path = "/delete/{dishId}")
    public boolean deleteDish(@PathVariable int dishId){return dishDao.deleteDish(dishId);}


}
