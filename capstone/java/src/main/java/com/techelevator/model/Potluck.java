package com.techelevator.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Potluck {
    private int pluckId;
    private String pluckName;
    private String pluckPlace;
    private LocalDateTime pluckTime;
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String pluckDescription = "";
    private List<Integer> pluckDishes;
    private List<Integer> pluckCats;

    public Potluck(int pluckId, String pluckName) {
        this.pluckId = pluckId;
        this.pluckName = pluckName;
    }

    public Potluck() {
    }

    public int getPluckId() {
        return pluckId;
    }

    public void setPluckId(int pluckId) {
        this.pluckId = pluckId;
    }

    public String getPluckName() {
        return pluckName;
    }

    public void setPluckName(String pluckName) {
        this.pluckName = pluckName;
    }

    public String getPluckPlace() {
        return pluckPlace;
    }

    public void setPluckPlace(String pluckPlace) {
        this.pluckPlace = pluckPlace;
    }

    public String getPluckDescription() {
        return pluckDescription;
    }

    public void setPluckDescription(String pluckDescription) {
        this.pluckDescription = pluckDescription;
    }

    public LocalDateTime getPluckTime() {
        return pluckTime;
    }

    public void setPluckTime(LocalDateTime pluckTime) {
        this.pluckTime = pluckTime;
    }

    public List<Integer> getPluckDishes() {
        return pluckDishes;
    }

    public void setPluckDishes(List<Integer> pluckDishes) {
        this.pluckDishes = pluckDishes;
    }

    public List<Integer> getPluckCats() {
        return pluckCats;
    }

    public void setPluckCats(List<Integer> pluckCats) {
        this.pluckCats = pluckCats;
    }
}
