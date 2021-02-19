package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
                .requiredMatchLevel(data.getRequiredMatchLevel())
                .build();
        positionRepository.save(newRegistration);
    }

    public List<Position> findAllPositionByCityAndPositionType(String city, String positionName) {
        return positionRepository.findAllByCityAndByNameOfPosition(city,positionName);
    }

    @Transactional
    public void deletePosition(Position position) {
        positionRepository.delete(position);
    }

    public Position getPositionById(UUID id) {
        return positionRepository.findById(id).get();
    }

    public Position updatePositionDetails(Position position) {
        return positionRepository.save(position);
    }
}
