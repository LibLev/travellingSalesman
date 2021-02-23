package com.codecool.travelling.repository;

import com.codecool.travelling.model.Salesman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SalesmanRepositoryTest {

    @Autowired
    SalesmanRepository salesmanRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void saveSimpleOne(){
        Salesman newSalesman = Salesman.builder()
                .username("Pisti")
                .firstname("István")
                .middleName("János")
                .lastname("Kovács")
                .password("password")
                .city("Budapest")
                .address("Batthiány tér")
                .houseNumber(1)
                .drivingLicense(true)
                .country("Hungary")
                .county("Pest")
                .nationality("Hungarian")
                .email("pista@kamu.hu")
                .birthDate(LocalDate.parse("2000-01-01"))
                .phoneNumber(301234567)
                .gender("male")
                .build();
        salesmanRepository.save(newSalesman);
        List<Salesman> salesmen = salesmanRepository.findAll();
        assertThat(salesmen).hasSize(1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueFieldTwice(){
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
                .password("password")
                .roles(Collections.singleton( "ROLE_USER"))
                .build();
        salesmanRepository.save(SF);

        Salesman SF2 = Salesman.builder()
                .username("SFF")
                .firstname("Sándor")
                .lastname("Ferenc")
                .middleName("Ferdinánd")
                .gender("male")
                .phoneNumber(307654321)
                .email("sandor.ferenc@kamu.hu")
                .birthDate(LocalDate.parse("1980-01-01"))
                .nationality("Hungarian")
                .country("Hungary")
                .county("Pest")
                .city("Budapest")
                .postcode(1013)
                .address("Szép Ilona utca")
                .houseNumber(23)
                .onePosition("Porszívó értékesítő","MédiaMarkt")
                .oneDegree("Diploma", "Budapesti Gazdasági Egyetem")
                .language("english B2")
                .drivingLicense(false)
                .password("password")
                .roles(Collections.singleton( "ROLE_USER"))
                .build();
        salesmanRepository.saveAndFlush(SF2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void emailShouldBeNotNull(){
        Salesman SF = Salesman.builder()
                .username("SF")
                .firstname("Sándor")
                .lastname("Ferenc")
                .middleName("Péter")
                .gender("male")
                .phoneNumber(301234567)
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
                .password("password")
                .roles(Collections.singleton( "ROLE_USER"))
                .build();
        salesmanRepository.save(SF);
    }

}