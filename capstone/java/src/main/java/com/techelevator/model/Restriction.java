package com.techelevator.model;

public class Restriction {

    private int id;
    private String name;
    private String initials;
    private boolean isActive;

    public Restriction(){}

    public Restriction(int id, String name, String initials, boolean isActive){
        this.id = id;
        this.name = name;
        this.initials = initials;
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

    public String getInitials() {
        return initials;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

//    public String toString() {
//        return "BREAD.";
//    }
}
