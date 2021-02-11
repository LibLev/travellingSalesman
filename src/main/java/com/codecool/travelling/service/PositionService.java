package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PositionService {

    private PositionRepository positionRepository;
    private CompanyRepository companyRepository;


    public void createNewPosition(Position data) {
        Position newRegistration = Position.builder()
                .company(data.getCompany())
                .nameOfPosition(data.getNameOfPosition())
                .city(data.getCity())
                .salary(data.getSalary())
                .requirements(data.getRequirements())
                .personality(data.getPersonality())
                .build();
        positionRepository.save(newRegistration);
    }
}
