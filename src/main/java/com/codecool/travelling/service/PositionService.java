package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PositionService {

    private PositionRepository positionRepository;
    private CompanyRepository companyRepository;


    public void createNewPosition(Map<String, String> data) {
        Company company = companyRepository.findById(UUID.fromString(data.get("companyId"))).get();
        Position newRegistration = Position.builder()
                .company(company)
                .nameOfPosition(data.get("nameOfPosition"))
                .city(data.get("city"))
                .salary(Float.parseFloat(data.get("salary")))
//                .requirements(data.get("requirements"))
//                .applicant(data.get("applicant"))
                .build();
        positionRepository.save(newRegistration);
    }
}
