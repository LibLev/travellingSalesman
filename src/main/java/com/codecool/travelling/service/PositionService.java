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
                .personality(data.getPersonality())
                .build();
        positionRepository.save(newRegistration);
    }

    public List<Position> matchPersonToPositionBasedOnPersonality(Salesman salesman) {
        List<Position> matchingPositionsList = new ArrayList<>();

        MATCH_LEVEL matchLevel = salesman.getMatchLevel();

        List<Position> positions = positionRepository.findAll();
        ListIterator<Position> allPositions = positions.listIterator();
        while (allPositions.hasNext()) {
            if ((allPositions.next().getRequiredMatchLevel()).equals(MATCH_LEVEL.NOT_RECOMMENDED)) {
                matchingPositionsList.add(allPositions.next());
                break;
            } else if ((allPositions.next().getRequiredMatchLevel()).equals(MATCH_LEVEL.ACCEPTABLE)) {
                if (!matchLevel.equals(MATCH_LEVEL.NOT_RECOMMENDED)) {
                    matchingPositionsList.add(allPositions.next());
                    break;
                }
            } else if ((allPositions.next().getRequiredMatchLevel()).equals(MATCH_LEVEL.RECOMMENDED)) {
                if (!matchLevel.equals(MATCH_LEVEL.NOT_RECOMMENDED) || !matchLevel.equals(MATCH_LEVEL.ACCEPTABLE)) {
                    matchingPositionsList.add(allPositions.next());
                    break;
                }
            } else {
                if (matchLevel.equals(MATCH_LEVEL.PERFECT)) {
                    matchingPositionsList.add(allPositions.next());
                    break;
                }
            }
        }
        log.info("MATCHING_POSITION:" + matchingPositionsList);
        return matchingPositionsList;
    }
}
