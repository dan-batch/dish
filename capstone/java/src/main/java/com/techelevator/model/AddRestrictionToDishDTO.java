package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class AddRestrictionToDishDTO {
    @NotNull
    private int dishId;
    @NotNull
    private int restrictionId;

    public AddRestrictionToDishDTO(int dishId, int restrictionId) {
        this.dishId = dishId;
        this.restrictionId = restrictionId;
    }

    public AddRestrictionToDishDTO() {
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(int restrictionId) {
        this.restrictionId = restrictionId;
    }
}
