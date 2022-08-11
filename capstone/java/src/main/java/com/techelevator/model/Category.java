package com.techelevator.model;

public class Category {
    private int catId;
    private String catName;
    private int limit;

    public Category(int catId, String catName, int limit) {
        this.catId = catId;
        this.catName = catName;
    }

    public Category(){}

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

    public int getLimit(){
        return limit;
    }

    public void setLimit(int limit){
        this.limit=limit;
    }
}
