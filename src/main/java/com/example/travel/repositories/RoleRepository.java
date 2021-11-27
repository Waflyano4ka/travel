package com.example.travel.repositories;

import com.example.travel.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByNameContaining(String name);
    List<Role> findByName(String name);
}
