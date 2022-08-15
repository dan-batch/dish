package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddCatToPluckDTO {

    @NotNull
    private int pluckId;

    @NotNull
    private int catId;

    public AddCatToPluckDTO(int pluckId, int catId) {
        this.pluckId = pluckId;
        this.catId = catId;
    }

    public AddCatToPluckDTO() {
    }

    public int getPluckId() {
        return pluckId;
    }

    public void setPluckId(int pluckId) {
        this.pluckId = pluckId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
}
