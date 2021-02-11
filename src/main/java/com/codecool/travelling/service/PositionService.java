package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PositionService {

    private PositionRepository positionRepository;
    private CompanyRepository companyRepository;
    private PersonalityService personalityService;


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

    public List<Position> matchPersonToPositionBasedOnPersonality(Salesman salesman) {
        List<Position> matchingPositions = new ArrayList<>();
        Optional<MATCH_LEVEL> matchLevel = Optional.of(personalityService.matchPersonToRole(salesman.getPersonality()).get());



        return matchingPositions;
    }
}
