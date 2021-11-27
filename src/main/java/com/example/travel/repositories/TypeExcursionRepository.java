package com.example.travel.repositories;

import com.example.travel.models.TypeExcursion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeExcursionRepository extends CrudRepository<TypeExcursion, Long> {
    List<TypeExcursion> findByNameContaining(String name);
    List<TypeExcursion> findByName(String name);
}
