package com.codecool.travelling.service;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    public List<Position> matchPersonToPositionBasedOnPersonality(Salesman salesman) {
        List<Position> matchingPositionsList = new ArrayList<>();

        MATCH_LEVEL matchLevel = salesman.getMatchLevel();

        List<Position> positions = positionRepository.findAll();
        for (Position position: positions) {
            if (position.getRequiredMatchLevel().equals(MATCH_LEVEL.NOT_RECOMMENDED)){
                matchingPositionsList.add(position);
                break;
            }else if ((position.getRequiredMatchLevel()).equals(MATCH_LEVEL.ACCEPTABLE)) {
                if (!matchLevel.equals(MATCH_LEVEL.NOT_RECOMMENDED)) {
                    matchingPositionsList.add(position);
                    break;
                }
            } else if ((position.getRequiredMatchLevel()).equals(MATCH_LEVEL.RECOMMENDED)) {
                if (!matchLevel.equals(MATCH_LEVEL.NOT_RECOMMENDED) || !matchLevel.equals(MATCH_LEVEL.ACCEPTABLE)) {
                    matchingPositionsList.add(position);
                    break;
                }
            } else {
                if (matchLevel.equals(MATCH_LEVEL.PERFECT)) {
                    matchingPositionsList.add(position);
                    break;
                }
            }
        }
        log.info("MATCHING_POSITION:" + matchingPositionsList);
        return matchingPositionsList;
    }
}
