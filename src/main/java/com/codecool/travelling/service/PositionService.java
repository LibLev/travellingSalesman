package com.codecool.travelling.service;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        MATCH_LEVEL matchLevel = salesman.getMatch_level();



        return matchingPositions;
    }
}
