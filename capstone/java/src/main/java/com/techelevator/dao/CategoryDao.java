package com.techelevator.dao;

import com.techelevator.model.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getAllCats();

    List<Category> getAllCatsByPluck(int pluckId);

    Category getCatById(int catId);

    int getCatLimitByPluck (int pluckId, int catId);

}
