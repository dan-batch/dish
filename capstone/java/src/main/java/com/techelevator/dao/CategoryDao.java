package com.techelevator.dao;

import com.techelevator.model.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getAllCats();

    List<Category> getAllCatsByPluck(int pluckId);

    Boolean addCatToPluck(int pluckId, int catId, int limit);

    Boolean updateLimit(int pluckId, int catId, int limit);

    Category getCatById(int catId);

    Category getCatByPluck (int pluckId, int catId);

}
