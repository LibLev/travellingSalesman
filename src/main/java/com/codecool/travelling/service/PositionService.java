package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionService {

    private PositionRepository positionRepository;


    public void createNewPosition(Position data) {
        Position newRegistration = Position.builder()
                .company(data.getCompany())
                .nameOfPosition(data.getNameOfPosition())
                .city(data.getCity())
                .salary(data.getSalary())
                .requirements(data.getRequirements())
                .requiredPersonality(data.getRequiredPersonality())
                .build();
        positionRepository.save(newRegistration);
    }
}
