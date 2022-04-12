package com.springmvc.repository.entries;

import com.springmvc.model.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private Float lat;
    private Float lon;
    private String city;
    private String state;
    @ElementCollection
    private List<Double> temperatures;

    public WeatherEntity(Weather weather) {
        date = weather.getDate();
        lat = weather.getLat();
        lon = weather.getLon();
        city = weather.getCity();
        state = weather.getState();
        temperatures = weather.getTemperatures();
    }
}
