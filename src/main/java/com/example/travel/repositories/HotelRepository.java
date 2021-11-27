package com.example.travel.repositories;

import com.example.travel.models.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findByNameContaining(String name);
    List<Hotel> findByName(String name);
}
