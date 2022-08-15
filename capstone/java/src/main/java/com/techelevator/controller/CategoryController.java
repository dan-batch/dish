package com.techelevator.controller;

import com.techelevator.dao.CategoryDao;
import com.techelevator.model.AddCatToPluckDTO;
import com.techelevator.model.Category;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/cat")
@CrossOrigin

public class CategoryController {

    private CategoryDao categoryDao;

    private CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping(path = "")
    public List<Category> getAllCats() {
        return categoryDao.getAllCats();
    }

    @GetMapping(path = "/pluck{pluckId}")
    public List<Category> getAllCatsByPluck(@PathVariable int pluckId) {
        return categoryDao.getAllCatsByPluck(pluckId);
    }

    @PostMapping(path = "/addCat")
    public boolean addCat(@RequestBody AddCatToPluckDTO newCat){
        return categoryDao.addCatToPluck(newCat.getPluckId(), newCat.getCatId());
    }

    @PutMapping(path = "/updateLimit") //todo: test with values in DB
    public boolean updateLimit(int pluckId, @RequestBody @Valid Category cat) {
        return categoryDao.updateLimit(pluckId, cat.getCatId(), cat.getLimit());
    }

    @GetMapping(path = "/cat{catId}")
    public Category getCatById(@PathVariable int catId) {
        return categoryDao.getCatById(catId);
    }

    @GetMapping(path = "/pluck{pluckId}cat{catId}")//todo: test with values in DB
    public Category getCatByPluck(@PathVariable int pluckId, @PathVariable int catId) {
        return categoryDao.getCatByPluck(pluckId, catId);
    }
}
