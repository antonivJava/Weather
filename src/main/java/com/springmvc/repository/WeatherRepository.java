package com.springmvc.repository;

import com.springmvc.repository.entries.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {

    WeatherEntity findById(int id);

    List<WeatherEntity> findByDate(Date date);

    List<WeatherEntity> findByCityIgnoreCaseIn(List<String> city);
}