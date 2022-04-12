package com.springmvc.service;

import com.springmvc.controller.exceptions.WeatherNotFoundException;
import com.springmvc.model.Weather;
import com.springmvc.repository.WeatherRepository;
import com.springmvc.repository.entries.WeatherEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    private WeatherRepository repository;
    private final SimpleDateFormat formatter;

    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public Weather saveWeather(Weather weather) {
        WeatherEntity entity = new WeatherEntity(weather);
        repository.save(entity);
        return new Weather(entity);
    }

    public Weather getWeatherById(int id) {
        WeatherEntity entity = repository.findById(id);

        if (entity == null) {
            throw new WeatherNotFoundException(id);
        }
        return new Weather(entity);
    }

    public List<Weather> getWeather(String dateStr, String sort, List<String> cities) throws ParseException {
        List<WeatherEntity> entities;

        if (dateStr != null) {
            Date date = formatter.parse(dateStr);
            entities = repository.findByDate(date);
        } else if (cities != null) {
            entities = repository.findByCityIgnoreCaseIn(cities);
        } else {
            entities = repository.findAll();
        }

        Comparator comparator;
        if (sort != null) {
            if (sort.equals("date")) {
                comparator = Comparator.comparing(WeatherEntity::getDate);
            } else {
                comparator = Comparator.comparing(WeatherEntity::getDate, Comparator.reverseOrder());
            }
            entities.sort(comparator);
        }
        return entities.stream().map(Weather::new).collect(Collectors.toList());
    }
}
