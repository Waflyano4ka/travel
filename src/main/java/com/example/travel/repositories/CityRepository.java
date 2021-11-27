package com.example.travel.repositories;

import com.example.travel.models.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByNameContaining(String name);
    List<City> findByName(String name);
}
