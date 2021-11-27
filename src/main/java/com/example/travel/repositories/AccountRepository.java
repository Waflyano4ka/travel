package com.example.travel.repositories;

import com.example.travel.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUsernameContaining(String name);
    List<Account> findByUsername(String name);
}
