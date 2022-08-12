package com.techelevator.model;

import java.util.List;

public class Dish {

    private int dishId;
    private String dishDescription;
    private String dishName;
    private String dishUserName;
    private int dishUserId;
    private List<Integer> dishRestrictions;
    private int dishCatId;
    private int dishPluckId;
    private int servings;


    public Dish(int dishId, String dishName, int dishUserId, int dishCatId) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishUserId = dishUserId;
        this.dishCatId = dishCatId;
    }

    public Dish(){}

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishUserName() {
        return dishUserName;
    }

    public void setDishUserName(String dishUserName) {
        this.dishUserName = dishUserName;
    }

    public int getDishUserId() {
        return dishUserId;
    }

    public void setDishUserId(int dishUserId) {
        this.dishUserId = dishUserId;
    }

    public List<Integer> getDishRestrictions() {
        return dishRestrictions;
    }

    public void setDishRestrictions(List<Integer> dishRestrictions) {
        this.dishRestrictions = dishRestrictions;
    }

    public int getDishCatId() {
        return dishCatId;
    }

    public void setDishCatId(int dishCatId) {
        this.dishCatId = dishCatId;
    }

    public int getDishPluckId() {
        return dishPluckId;
    }

    public void setDishPluckId(int dishPluckId) {
        this.dishPluckId = dishPluckId;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
