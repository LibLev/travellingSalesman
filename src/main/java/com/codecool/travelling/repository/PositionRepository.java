package com.codecool.travelling.repository;

import com.codecool.travelling.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {

    @Query("SELECT p FROM Position p WHERE p.city = :city and p.nameOfPosition = :positionName")
    List<Position> findAllByCityAndByNameOfPosition(String city, String positionName);
}
