package com.techelevator.model;

public class Restriction {

    private int id;
    private String name;
    private String abbreviation;
    private boolean isActive;

    public Restriction() {
    }

    public Restriction(int id, String name, String abbreviation, boolean isActive) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.isActive = isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

//    public String toString() {
//        return "BREAD.";
//    }
}
