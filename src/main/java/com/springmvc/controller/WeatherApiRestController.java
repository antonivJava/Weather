package com.springmvc.controller;

import com.springmvc.model.Weather;
import com.springmvc.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
public class WeatherApiRestController {
    @Autowired
    WeatherService service;

    @PostMapping("/weather")
    public ResponseEntity<Weather> saveWeather(@RequestBody Weather weather) {
        return new ResponseEntity<>(service.saveWeather(weather), HttpStatus.CREATED);
    }

    @GetMapping("/weather/{id}")
    public ResponseEntity<Weather> getWeatherById(@PathVariable int id) {
        return new ResponseEntity<>(service.getWeatherById(id), HttpStatus.OK);
    }

    @GetMapping("/weather")
    public ResponseEntity<List<Weather>> getWeather(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "city", required = false) List<String> cities) throws ParseException {
        return new ResponseEntity<>(service.getWeather(date, sort, cities), HttpStatus.OK);
    }
}
