package com.codecool.travelling.repository;

import com.codecool.travelling.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SalesmanRepository extends JpaRepository<Salesman, UUID> {

    Optional<Salesman> findByEmail(String email);
}
