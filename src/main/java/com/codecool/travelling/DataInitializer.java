package com.codecool.travelling;


import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final SalesmanRepository salesmanRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;

    public DataInitializer(PositionRepository positionRepository,SalesmanRepository salesmanRepository, CompanyRepository companyRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.salesmanRepository = salesmanRepository;
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void run(String... args) {

        Salesman SF = Salesman.builder()
                .username("SF")
                .firstname("Sam")
                .lastname("Francisco")
                .middleName("Peters")
                .gender("male")
                .phoneNumber(301234567)
                .email("sam.francisco@kamu.hu")
                .birthDate(LocalDate.parse("1980-01-01"))
                .nationality("Canadian")
                .country("Canada")
                .county("Toronto")
                .city("Toronto")
                .postcode(74568)
                .address("King street")
                .houseNumber(66)
                .drivingLicense(true)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "SALESMAN"))
                .build();
        salesmanRepository.save(SF);

        log.info(salesmanRepository.findByUsername("SF").get().toString());

        Company CC = Company.builder()
                .username("CC")
                .nameOfCompany("Company Company")
                .country("Canada")
                .county("Toronto")
                .postcode(74570)
                .city("Toronto")
                .address("Knights avenue")
                .houseNumber(55)
                .phoneNumber(306715264)
                .email("company.company@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-01"))
                .taxNumber("123456-7-89")
                .roles(Collections.singleton("COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(CC);

        Personality requiredPersonality = Personality.builder()
                .adaptability(7)
                .assertiveness(7)
                .attitude(7)
                .calculation(7)
                .compliance(7)
                .creativity(7)
                .decisionMaking(7)
                .energyLevel(7)
                .entrepreneurship(7)
                .humanityFocus(7)
                .independence(7)
                .numberComprehension(7)
                .objectiveDecisionMaking(7)
                .readingLiteracy(7)
                .socialContacts(7)
                .studyIndex(7)
                .vocabulary(7)
                .build();

        Position newRegistration = Position.builder()
                .company(CC)
                .nameOfPosition("Autó értékesítő")
                .city("Budapest")
                .salary(350000)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .personality(requiredPersonality)
                .build();
        positionRepository.save(newRegistration);

    }
}
