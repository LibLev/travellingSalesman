package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
public class CompanyService {

    private PasswordEncoder passwordEncoder;
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.companyRepository = companyRepository;
    }

    public void createNewCompany(Map<String, String> data) {
        Company newRegistration = Company.builder()
                .username(data.get("username"))
                .nameOfCompany(data.get("nameOfCompany"))
                .country(data.get("country"))
                .county(data.get("county"))
                .postcode(Integer.parseInt(data.get("postcode")))
                .city(data.get("city"))
                .address(data.get("address"))
                .houseNumber(Integer.parseInt(data.get("houseNumber")))
                .phoneNumber(Integer.parseInt(data.get("phoneNumber")))
                .email(data.get("email"))
                .dateOfFoundation(LocalDate.parse(data.get("dateOfFoundation")))
                .taxNumber(data.get("taxNumber"))
                .roles(Collections.singleton("ROLE_COMPANY"))
                .password(passwordEncoder.encode(data.get("password")))
                .build();
        companyRepository.save(newRegistration);
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id).get();
    }

    public Company updateCompanyData(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteProfile(Company company) {
        companyRepository.delete(company);
    }
}
