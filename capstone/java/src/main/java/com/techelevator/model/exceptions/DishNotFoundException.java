package com.techelevator.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Dish Not Found.")
public class DishNotFoundException extends RuntimeException {

}