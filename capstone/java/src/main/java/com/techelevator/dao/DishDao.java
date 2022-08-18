package com.techelevator.dao;

import com.techelevator.model.Dish;
import com.techelevator.model.Category;
import com.techelevator.model.Potluck;
import com.techelevator.model.Restriction;

import java.util.List;

public interface DishDao {
    List<Dish> getAllDishes();
   //list of all dishes added across whole site

    //create a new dish

    Boolean createDish(int pluckId, int catId, int userId, String dishName, String dishDescription, int servings);

    Boolean updateDish(int dishId, String dishDescription, String dishName, int servings, String username);

    Dish getDishById(int dishId);
    //one specific dish

    List<Dish> getDishesByUserAndPluck(int userId, int pluckId);
    //list of what dishes a specific user is bringing to a specific pluck


    List<Dish> getDishesByPluckId(int pluckId);
    //all dishes added to a specific pluck


    List<Dish> getDishesByCatAndPluck(int catId, int pluckId);
    //all dishes added to a specific category of a specific pluck


    Category getCategoryByDishId(int dishId);
    //what category a dish is associated with (returns whole category object)

    Potluck getPluckByDishId(int dishId);
    //what pluck a dish is associated with (returns whole pluck object)


    Boolean addRestriction(int dishId, int restrictionId);
    //adds restriction to dish

    Boolean deleteDish(int dishId);



}
