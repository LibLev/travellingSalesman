package com.codecool.travelling.repository;

import com.codecool.travelling.model.Salesman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
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

}