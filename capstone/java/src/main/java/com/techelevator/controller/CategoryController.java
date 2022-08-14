package com.techelevator.controller;

import com.techelevator.dao.CategoryDao;
import com.techelevator.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/cat")
@CrossOrigin

public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping(path = "")
    public List<Category> getAllCats(){return categoryDao.getAllCats();}
}
