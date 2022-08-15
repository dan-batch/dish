package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class Category {
    private int catId;
    private String catName;
    private int limit;

    //todo: in controller, set limits for 1-10, then limit "11" = no limit
    public Category(int catId, String catName, int limit) {
        this.catId = catId;
        this.catName = catName;
    }

    public Category() {
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public static class AddDishToPluckDTO {
        @NotEmpty
        private int pluckId;

        @NotEmpty
        private int catId;
    }
}
