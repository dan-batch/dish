package com.techelevator.dao;

import com.techelevator.model.Category;
import com.techelevator.model.Dish;
import com.techelevator.model.Potluck;
import com.techelevator.model.Restriction;
import com.techelevator.model.exceptions.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcDishDao implements DishDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcDishDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Dish> getAllDishes() {
        String sql = "SELECT dish_id, pluck_id, cat_id, user_id, dish_name, username, servings, description FROM pluck_dish";
        SqlRowSet allDishes = jdbcTemplate.queryForRowSet(sql);
        List<Dish> dishList = new ArrayList<>();

        while (allDishes.next()) {
            dishList.add(mapRowToDish(allDishes));
        }
        return dishList;
    }

    @Override
    public Boolean createDish(int pluckId, int catId, int userId, String dishName, String username) {
        String sql = "INSERT INTO pluck_dish (pluck_id, cat_id, user_id, dish_name) VALUES (?,?,?,?,?)";
        try {
            if (jdbcTemplate.update(sql, pluckId, catId, userId, dishName, username) == 1) {
                return true;
            } else {
                System.err.println("The dish could not be created");
                return false;
            }
        } catch (PotluckNotFoundException p){
            throw new PotluckNotFoundException();
        }catch (CategoryNotFoundException c){
            throw new CategoryNotFoundException();
        }catch (UserNotFoundException u){
            throw new UserNotFoundException();
        }
    }

    @Override
    public Boolean updateDish(int dishId, String dishDescription, String dishName, int servings, String username) {
        String sql = "UPDATE pluck_dish SET description=?, dish_name=?, servings=?, username=? WHERE dishId=?";
        try {
            if (jdbcTemplate.update(sql, dishDescription, dishName, servings, username, dishId) == 1) {
                return true;
            } else {
                System.err.println("The dish could not be updated");
                return false;
            }
        } catch (DishNotFoundException d){
            throw new DishNotFoundException();
        }
    }

    @Override
    public Dish getDishById(int dishId) {
        String sql = "SELECT dish_id, pluck_id, cat_id, user_id, dish_name, servings, description FROM pluck_dish " +
                "WHERE dish_id = ?";

        SqlRowSet dishById = jdbcTemplate.queryForRowSet(sql, dishId);

        if (dishById.next()) {
            return (mapRowToDish(dishById));
        } else {
            throw new DishNotFoundException();
        }
    }

    @Override
    public List<Dish> getDishesByUserAndPluck(int userId, int pluckId) {
        String sql = "SELECT dish_id, pluck_id, cat_id, user_id, dish_name, servings, username, description FROM pluck_dish " +
                "WHERE user_id = ? AND pluck_id = ?";

        SqlRowSet dishById = jdbcTemplate.queryForRowSet(sql, userId, pluckId);
        List<Dish> dishList = new ArrayList<>();

        try {
            while (dishById.next()) {
                dishList.add(mapRowToDish(dishById));
                return dishList;
            }
        } catch (UserNotFoundException u) {
            throw new UserNotFoundException();
        } catch (PotluckNotFoundException p) {
            throw new PotluckNotFoundException();
        } return null;
    }

    @Override
    public List<Dish> getDishesByPluckId(int pluckId) {
        String sql = "SELECT dish_id, pluck_id, cat_id, user_id, dish_name, servings, description FROM pluck_dish " +
                "WHERE pluck_id = ?";

        SqlRowSet dishById = jdbcTemplate.queryForRowSet(sql, pluckId);
        List<Dish> dishList = new ArrayList<>();

        try {
            while (dishById.next()) {
                dishList.add(mapRowToDish(dishById));
            }return dishList;
        } catch (PotluckNotFoundException p){
            throw new PotluckNotFoundException();
        }
    }

    @Override
    public List<Dish> getDishesByCatAndPluck(int catId, int pluckId) {
        String sql = "SELECT dish_id, pluck_id, cat_id, user_id, dish_name, servings, username, description FROM pluck_dish " +
                "WHERE cat_id = ? AND pluck_id = ?";

        SqlRowSet dishById = jdbcTemplate.queryForRowSet(sql, catId, pluckId);
        List<Dish> dishList = new ArrayList<>();

        try {
            while (dishById.next()) {
                dishList.add(mapRowToDish(dishById));

            }return dishList;
        } catch (PotluckNotFoundException p){
            throw new PotluckNotFoundException();
        } catch (CategoryNotFoundException c){
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Category getCategoryByDishId(int dishId) {
        String sql = "SELECT category.cat_id, cat_name FROM category " +
                "JOIN pluck_dish AS pd ON pd.cat_id = category.cat_id " +
                "WHERE pd.dish_id = ?";
        SqlRowSet catByDish = jdbcTemplate.queryForRowSet(sql, dishId);

       try {
           if (catByDish.next()) {
               return (mapRowToCat(catByDish));
           }
           throw new CategoryNotFoundException();
       } catch (DishNotFoundException d){
           throw new DishNotFoundException();
       }

    }

    @Override
    public Potluck getPluckByDishId(int dishId) {
        String sql = "SELECT pluck.pluck_id, pluck_name, pluck_description, pluck_date_time, pluck_place " +
                "FROM pluck JOIN pluck_dish AS pd ON pd.pluck_id = pluck.pluck_id " +
                "WHERE pd.dish_id = ?";
        SqlRowSet pluckByDish = jdbcTemplate.queryForRowSet(sql, dishId);

        try {
            if (pluckByDish.next()) {
                return (mapRowToPluck(pluckByDish));
            }
            throw new PotluckNotFoundException();
        } catch (DishNotFoundException d){
            throw new DishNotFoundException();
        }
    }

    @Override
    public Boolean addRestriction(int dishId, int restrictionId) {
        String sql = "INSERT INTO dish_restrictions (dish_id, restriction_id) " +
                "VALUES (?,?)";
        try {
            if (jdbcTemplate.update(sql, dishId, restrictionId) == 1) {
                return true;
            }
            System.err.println("The restriction could not be added");
            return false;
        } catch (RestrictionNotFoundException r){
            throw new RestrictionNotFoundException();
        }
    }


    @Override
    public Boolean deleteDish(int dishId){
        String sql = "DELETE FROM pluck_dish WHERE dish_id = ?";
        try {
            if (jdbcTemplate.update(sql, dishId) == 1) {
                return true;
            }
            System.err.println("The dish could not be deleted");
            return false;
        } catch (DishNotFoundException d){
            throw new DishNotFoundException();
        }
    }


    private Dish mapRowToDish(SqlRowSet dishRowSet) {
        Dish dish = new Dish();
        dish.setDishId(dishRowSet.getInt("dish_id"));
        dish.setDishPluckId(dishRowSet.getInt("pluck_id"));
        dish.setDishCatId(dishRowSet.getInt("cat_id"));
        dish.setDishUserId(dishRowSet.getInt("user_id"));
        dish.setDishName(dishRowSet.getString("dish_name"));
        dish.setServings(dishRowSet.getInt("servings"));
//        dish.setUsername(dishRowSet.getString("username"));
        dish.setDishDescription(dishRowSet.getString("description"));
        return dish;
    }

    private Category mapRowToCat(SqlRowSet catRowSet) {
        Category cat = new Category();
        cat.setCatId(catRowSet.getInt("cat_id"));
        cat.setCatName(catRowSet.getString("cat_name"));
        return cat;
    }

    private Potluck mapRowToPluck(SqlRowSet pluckRowSet) {
        Potluck pluck = new Potluck();
        pluck.setPluckId(pluckRowSet.getInt("pluck_id"));
        pluck.setPluckName(pluckRowSet.getString("pluck_name"));
        pluck.setPluckDescription(pluckRowSet.getString("pluck_description"));
        if (pluckRowSet.getTimestamp("pluck_date_time") != null) {
            pluck.setPluckTime(pluckRowSet.getTimestamp("pluck_date_time").toLocalDateTime());
        } else {
            pluck.setPluckTime(null);
        }
        pluck.setPluckPlace(pluckRowSet.getString("pluck_place"));
        return pluck;
    }
}
