package com.techelevator.dao;

import com.techelevator.model.Potluck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface PotluckDao {

    List<Potluck> getAllPlucks();

    List<Potluck> getAllPlucksByUser(int userId);

    Boolean createPluck (int pluckId, String pluckName,
                        String pluckPlace, LocalDateTime pluckTime);

    Potluck updatePluck(int pluckId, String pluckName,
                        String pluckPlace, String pluckDescription,
                        LocalDateTime pluckTime, List<Integer> pluckDishes, List<Integer> pluckCats);

    Potluck getPluckById(int pluckId);

}
