package com.example.travel.repositories;

import com.example.travel.models.Excursion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExcursionRepository extends CrudRepository<Excursion, Long> {
    List<Excursion> findByNameContaining(String name);
    List<Excursion> findByName(String name);
}
