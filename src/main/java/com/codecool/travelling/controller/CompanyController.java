package com.codecool.travelling.controller;

import com.codecool.travelling.model.Company;
import com.codecool.travelling.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/company")
@RestController
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/get-company/{id}")
    public Company getCompanyDataById(@PathVariable UUID id){
        return companyService.getCompanyById(id);
    }

    @PutMapping("/update-profile")
    public Company updateProfileData(@RequestBody Company company){
        return companyService.updateCompanyData(company);
    }

    @DeleteMapping("/delete-profile")
    public @ResponseBody ResponseEntity<String> deleteCompanyProfile(@RequestBody Company company){
        companyService.deleteProfile(company);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
