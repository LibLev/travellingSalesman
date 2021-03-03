package com.codecool.travelling.repository;

import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PersonalityRepository extends JpaRepository<Personality, UUID> {

    @Query("SELECT p FROM Personality p WHERE p.salesman = :salesman")
    public Optional<Personality> findBySalesman(Salesman salesman);
}
