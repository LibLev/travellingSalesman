package com.codecool.travelling.service;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

@Service
public class PositionService {

    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void createNewPosition(Map<String, String> data) {
        Position newRegistration = Position.builder()
                .company(data.get("company"))
                .nameOfPosition(data.get("nameOfPosition"))
                .salary(Float.parseFloat(data.get("salary")))
//                .requirements(data.get("requirements"))
//                .applicant(data.get("applicant"))
                .build();
        positionRepository.save(newRegistration);
    }
}
