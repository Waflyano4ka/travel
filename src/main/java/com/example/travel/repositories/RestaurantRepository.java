package com.example.travel.repositories;

import com.example.travel.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByNameContaining(String name);
    List<Restaurant> findByName(String name);
}
