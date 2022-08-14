package com.techelevator.dao;

import com.techelevator.model.Category;
import com.techelevator.model.exceptions.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Category> getAllCats() {
        String sql = "SELECT cat_id, cat_name FROM category";
        SqlRowSet allCats = jdbcTemplate.queryForRowSet(sql);
        List<Category> catsList = new ArrayList<>();

        while (allCats.next()) {
            catsList.add(mapRowToCat(allCats));

        }
        return catsList;
    }

    @Override
    public List<Category> getAllCatsByPluck(int pluckId) {
        String sql = "SELECT cat_id, cat_limit FROM pluck_cat WHERE pluck_id = ?";
        SqlRowSet catsByPluck = jdbcTemplate.queryForRowSet(sql, pluckId);
        List<Category> catsList = new ArrayList<>();

        try {
            while (catsByPluck.next()) {
                catsList.add(mapRowToPluckCat(catsByPluck));
            }
            return catsList;
        } catch (PotluckNotFoundException p) {
            throw new PotluckNotFoundException();
        } catch (CategoryNotFoundException c) {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Boolean addCatToPluck(int pluckId, int catId, int limit) {
        String sql = "INSERT INTO pluck_cat (pluck_id, cat_id, cat_limit) VALUES (?,?,?)";

        try {
            if (jdbcTemplate.update(sql, pluckId, catId, limit) == 1) {
                return true;
            }
            System.err.println("The category could not be added");
            return false;
        } catch (PotluckNotFoundException p) {
            throw new PotluckNotFoundException();
        } catch (CategoryNotFoundException c) {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Boolean updateLimit(int pluckId, int catId, int limit) {
        String sql = "UPDATE pluck_cat SET limit = ? WHERE pluck_id = ? AND cat_id = ?";

        try {
            if (jdbcTemplate.update(sql, limit, pluckId, catId) == 1) {
                return true;
            }
            System.err.println("Couldn't update limit");
            return false;
        } catch (PotluckNotFoundException p) {
            throw new PotluckNotFoundException();
        } catch (CategoryNotFoundException c) {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Category getCatById(int catId) {
        String sql = "SELECT cat_id, cat_name FROM category WHERE cat_id = ?";
        SqlRowSet catById = jdbcTemplate.queryForRowSet(sql, catId);

        if (catById.next()) {
            return (mapRowToCat(catById));
        }
        throw new CategoryNotFoundException();
    }

    @Override
    public Category getCatByPluck(int pluckId, int catId) {
        String sql = "SELECT pluck_id, cat_id, cat_limit FROM pluck_cat WHERE pluck_id = ? AND cat_id = ?";
        SqlRowSet catByPluck = jdbcTemplate.queryForRowSet(sql, pluckId, catId);

        try {
            if (catByPluck.next()) {
                return (mapRowToPluckCat(catByPluck));
            }
            throw new CategoryNotFoundException();
        } catch (PotluckNotFoundException p) {
            throw new PotluckNotFoundException();
        }
    }

    private Category mapRowToCat(SqlRowSet catRowSet) {
        Category cat = new Category();
        cat.setCatId(catRowSet.getInt("cat_id"));
        cat.setCatName(catRowSet.getString("cat_name"));
        return cat;
    }

    private Category mapRowToPluckCat(SqlRowSet pluckCatRowSet) {
        Category cat = new Category();
        cat.setCatId(pluckCatRowSet.getInt("cat_id"));
        cat.setLimit(pluckCatRowSet.getInt("cat_limit"));
        return cat;
    }
}
