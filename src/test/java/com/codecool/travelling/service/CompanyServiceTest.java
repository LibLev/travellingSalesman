package com.codecool.travelling.service;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.repository.CompanyRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

public class CompanyServiceTest {

    private CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
    //private static Company company = Mockito.mock(Company.class);

    private CompanyService companyService = new CompanyService(companyRepository);


    /*@BeforeAll
    public static void init(){
        when(company.getId()).thenReturn(UUID.fromString("9ee504da-0eff-4d37-8a21-78b26c17d900"));
        when(company.getUsername()).thenReturn("CC");
        when(company.getNameOfCompany()).thenReturn("Codecool kft");
        when(company.getCountry()).thenReturn("Hungary");
        when(company.getCounty()).thenReturn("Pest");
        when(company.getPostcode()).thenReturn(1071);
        when(company.getPhoneNumber()).thenReturn(701234567);
        when(company.getCity()).thenReturn("Budapest");
        when(company.getAddress()).thenReturn("Nagymező utca");
        when(company.getHouseNumber()).thenReturn(12);
        when(company.getDateOfFoundation()).thenReturn(LocalDate.parse("2016-01-01"));
        when(company.getEmail()).thenReturn("codecool@codecool.com");
        when(company.getTaxNumber()).thenReturn("123456-7-89");
        when(company.getPassword()).thenReturn("password");
    }*/

    @Test
    public void testGetCompanyById(){
        Optional<Company> object = Optional.of(new Company());
        given(companyRepository.findById(Mockito.any(UUID.class))).willReturn(object);
        Company returnedCompany = companyService.getCompanyById(UUID.fromString("9ee504da-0eff-4d37-8a21-78b26c17d900"));
        Mockito.verify(companyRepository).findById(Mockito.any(UUID.class));
        assertNotNull(returnedCompany);
    }

}