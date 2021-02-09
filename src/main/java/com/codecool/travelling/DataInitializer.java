package com.codecool.travelling;


import com.codecool.travelling.model.Company;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final SalesmanRepository salesmanRepository;
    private final CompanyRepository companyRepository;

    public DataInitializer(SalesmanRepository salesmanRepository, CompanyRepository companyRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.salesmanRepository = salesmanRepository;
        this.companyRepository = companyRepository;
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
                .roles(Collections.singleton( "ROLE_USER"))
                .roles(Collections.singleton( "SALESMAN"))
                .build();
        salesmanRepository.save(SF);

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
                .roles(Collections.singleton("ROLE_USER"))
                .roles(Collections.singleton("COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(CC);

    }
}
