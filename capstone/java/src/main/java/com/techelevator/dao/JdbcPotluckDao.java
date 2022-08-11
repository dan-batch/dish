package com.techelevator.dao;

import com.techelevator.model.Potluck;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPotluckDao implements PotluckDao {
    private final JdbcTemplate jdbcTemplate;
    private PotluckDao potluckDao;

    public JdbcPotluckDao(JdbcTemplate jdbcTemplate, PotluckDao potluckDao){
        this.jdbcTemplate = jdbcTemplate;
        this.potluckDao = potluckDao;
    }


    @Override
    public List<Potluck> getAllPlucks() {
        return null;
    }

    @Override
    public List<Potluck> getAllPlucksByUser(int userId){
        return null;
    }


    @Override
    public Boolean createPluck(int pluckId, String pluckName, String pluckPlace, LocalDateTime pluckTime) {
        return null;
    }

    @Override
    public Potluck updatePluck(int pluckId, String pluckName, String pluckPlace, String pluckDescription, LocalDateTime pluckTime, List<Integer> pluckDishes, List<Integer> pluckCats) {
        return null;
    }

    @Override
    public Potluck getPluckById(int pluckId) {
        return null;
    }
}
