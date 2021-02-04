package com.codecool.travelling.repository;

import com.codecool.travelling.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findByEmail(String email);

    Optional<Company> findByUsername(String username);
}
