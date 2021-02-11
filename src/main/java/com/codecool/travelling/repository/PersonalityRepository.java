package com.codecool.travelling.repository;

import com.codecool.travelling.model.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonalityRepository extends JpaRepository<Personality, UUID> {
}
