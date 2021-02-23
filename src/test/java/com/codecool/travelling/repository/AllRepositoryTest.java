package com.codecool.travelling.repository;

import com.codecool.travelling.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AllRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    SalesmanRepository salesmanRepository;
    @Autowired
    PersonalityRepository personalityRepository;
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void positionPersistedWithCompany(){
        Position KCPosition = Position.builder()
                .nameOfPosition("Fegyver kereskedő")
                .city("Budapest")
                .salary(700000)
                .requiredMatchLevel(MATCH_LEVEL.PERFECT)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();

        Company KC = Company.builder()
                .username("KC")
                .nameOfCompany("KAPTAIN Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1013)
                .city("Budapest")
                .address("Batthiány tér")
                .houseNumber(4)
                .phoneNumber(306723464)
                .email("company.company2@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-01"))
                .taxNumber("123456-3-89")
                .roles(Collections.singleton("ROLE_ADMIN"))
                .password("password")
                .position(KCPosition)
                .build();
        companyRepository.save(KC);

        List<Position> positions = positionRepository.findAll();
        assertThat(positions).hasSize(1).allMatch(position -> position.getId() != null);
    }

    @Test
    public void personalityPersistedWithSalesman(){
        Personality salesmanPersonality = Personality.builder()
                .energyLevel(6)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(8)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(7)
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
                .password("password")
                .roles(Collections.singleton( "ROLE_USER"))
                .build();
        salesmanRepository.save(SF);

        List<Personality> personalities = personalityRepository.findAll();
        assertThat(personalities).hasSize(1).allMatch(personality -> personality.getId() != null);
    }
}
