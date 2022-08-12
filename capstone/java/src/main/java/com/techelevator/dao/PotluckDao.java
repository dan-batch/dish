package com.techelevator.dao;

import com.techelevator.model.Potluck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface PotluckDao {

    List<Potluck> getAllPlucks();

    List<Potluck> getAllPlucksByUser(int userId);

    Boolean createPluck (String pluckName,
                         LocalDateTime pluckTime, String pluckPlace);

    Boolean updatePluck(int pluckId, String pluckName, String pluckDescription, LocalDateTime pluckTime,  String pluckPlace);

    Potluck getPluckById(int pluckId);

    Boolean addDish(int dishId, int pluckId, int cat_id, int user_id, String dish_name);

    Boolean addCat(int pluckId, int catId);

}
