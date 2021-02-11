package com.codecool.travelling;


import com.codecool.travelling.model.*;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PersonalityRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import com.codecool.travelling.service.PersonalityService;
import com.codecool.travelling.service.PositionService;
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
    private final PersonalityService personalityService;
    private final PersonalityRepository personalityRepository;
    private final PositionService positionService;

    public DataInitializer(PersonalityRepository personalityRepository,PositionService positionService,PersonalityService personalityService,PositionRepository positionRepository,SalesmanRepository salesmanRepository, CompanyRepository companyRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.salesmanRepository = salesmanRepository;
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
        this.personalityService = personalityService;
        this.positionService = positionService;
        this.personalityRepository = personalityRepository;
    }

    @Override
    public void run(String... args) {

        Personality salesmanPersonality = Personality.builder()
                .energyLevel(6)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(8)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(8)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(8)
                .studyIndex(7)
                .vocabulary(7)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(2)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();

        Salesman SF = Salesman.builder()
                .username("SF")
                .firstname("Sándor")
                .lastname("Ferenc")
                .middleName("Péter")
                .gender("male")
                .phoneNumber(301234567)
                .email("sandor.ferenc@kamu.hu")
                .birthDate(LocalDate.parse("1980-01-01"))
                .nationality("Hungarian")
                .country("Hungary")
                .county("Pest")
                .city("Budapest")
                .postcode(1012)
                .address("Szabó Ilonka utca")
                .houseNumber(32)
                .onePosition("Autó értékesítő","Csonti Car")
                .oneDegree("Diploma", "Budapesti Gazdasági Egyetem")
                .language("english B2")
                .drivingLicense(true)
                .personality(salesmanPersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "SALESMAN"))
                .build();
        salesmanPersonality.setSalesman(SF);
        salesmanRepository.save(SF);

        log.info(salesmanRepository.findByUsername("SF").get().toString());

        Company CC = Company.builder()
                .username("CC")
                .nameOfCompany("Company Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1011)
                .city("Budapest")
                .address("Gyorskocsi utca")
                .houseNumber(1)
                .phoneNumber(306715264)
                .email("company.company@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-01"))
                .taxNumber("123456-7-89")
                .roles(Collections.singleton("COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(CC);

        Position newPosition = Position.builder()
                .company(CC)
                .nameOfPosition("Autó értékesítő")
                .city("Budapest")
                .salary(350000)
                .requiredMatchLevel(MATCH_LEVEL.RECOMMENDED)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();
        positionRepository.save(newPosition);



        personalityService.matchPersonToRole(SF);
        personalityService.matchPersonToPositionsBasedOnPersonality(SF);

    }
}
