package com.springmvc.controller.exceptions;

public class WeatherNotFoundException extends RuntimeException{

    public WeatherNotFoundException(int id) {
        super(String.format("Could not find weather with id='%s'", id));
    }
}
