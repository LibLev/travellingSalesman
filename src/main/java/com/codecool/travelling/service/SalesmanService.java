package com.codecool.travelling.service;

import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.SalesmanRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
public class SalesmanService {

    private PasswordEncoder passwordEncoder;

    private SalesmanRepository salesmanRepository;

    public SalesmanService(SalesmanRepository salesmanRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.salesmanRepository = salesmanRepository;
    }


    public void createNewSalesman(Map<String, String> data) {
        Salesman newRegistration = Salesman.builder()
                .username(data.get("username"))
                .firstname(data.get("firstname"))
                .lastname(data.get("lastname"))
                .middleName(data.get("middleName"))
                .gender(data.get("gender"))
                .phoneNumber(Integer.parseInt(data.get("phoneNumber")))
                .email(data.get("email"))
                .birthDate(LocalDate.parse(data.get("birthDate")))
                .nationality(data.get("nationality"))
                .country(data.get("country"))
                .county(data.get("county"))
                .city(data.get("city"))
                .postcode(Integer.parseInt(data.get("postcode")))
                .address(data.get("address"))
                .houseNumber(Integer.parseInt(data.get("houseNumber")))
                .drivingLicense(Boolean.parseBoolean(data.get("drivingLicense")))
                .password(passwordEncoder.encode(data.get("password")))
                .roles(Collections.singleton( "ROLE_USER"))
                //.roles(Collections.singleton( "SALESMAN"))
                .build();
        salesmanRepository.save(newRegistration);
    }

    public Salesman getSalesman(UUID id) {
        return salesmanRepository.findById(id).get();
    }
}
