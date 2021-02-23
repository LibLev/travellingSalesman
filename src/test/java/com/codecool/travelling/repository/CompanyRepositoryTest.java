package com.codecool.travelling.repository;

import com.codecool.travelling.model.Company;
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
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void saveSimpleOne(){
        Company newCompany = Company.builder()
                .username("CC")
                .city("Budapest")
                .dateOfFoundation(LocalDate.parse("2015-01-01"))
                .taxNumber("123456-7-89")
                .phoneNumber(301234567)
                .address("Nagymező utca")
                .houseNumber(1)
                .email("codecool@codecool.hu")
                .nameOfCompany("Codecool Kft.")
                .country("Hungary")
                .county("Pest")
                .password("password")
                .build();
        companyRepository.save(newCompany);
        List<Company> companies = companyRepository.findAll();
        assertThat(companies).hasSize(1);
    }

}