package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Potluck {
    private int pluckId;
    @NotEmpty
    private String pluckName;
    @NotEmpty
    private String pluckPlace;
    @NotNull
    //https://stackoverflow.com/questions/61202937/json-parse-error-cannot-deserialize-value-of-type-java-time-localdatetime-fro
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime pluckTime;
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String pluckDescription;
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
