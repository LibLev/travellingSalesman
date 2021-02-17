package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
                .build();
        positionRepository.save(newRegistration);
    }

    public List<Position> findAllPositionByCityAndPositionType(String city, String positionName) {
        return positionRepository.findAllByCityAndByNameOfPosition(city,positionName);
    }
}
