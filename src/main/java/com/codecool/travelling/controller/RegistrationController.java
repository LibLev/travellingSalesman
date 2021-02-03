package com.codecool.travelling.controller;

import com.codecool.travelling.service.CompanyService;
import com.codecool.travelling.service.SalesmanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class RegistrationController {

    private SalesmanService salesmanService;
    private CompanyService companyService;

    @PostMapping("/registration")
    public void registration(@RequestBody Map<String, String> data){
        String registrationType = data.get("registrationType");
        if (registrationType.equals("salesmanRegistration")){
            salesmanService.createNewSalesman(data);
        }else {
            companyService.createNewCompany(data);
        }
    }
}
