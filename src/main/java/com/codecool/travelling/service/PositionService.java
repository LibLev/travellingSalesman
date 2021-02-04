package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PositionService {

    private PositionRepository positionRepository;
    private CompanyRepository companyRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }


    public void createNewPosition(Map<String, String> data) {
        Company company = companyRepository.findById(UUID.fromString(data.get("companyId"))).get();
        Position newRegistration = Position.builder()
                .company(company)
                .nameOfPosition(data.get("nameOfPosition"))
                .salary(Float.parseFloat(data.get("salary")))
//                .requirements(data.get("requirements"))
//                .applicant(data.get("applicant"))
                .build();
        positionRepository.save(newRegistration);
    }
}
