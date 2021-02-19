package com.codecool.travelling.controller;

import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.service.SalesmanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/salesman")
@AllArgsConstructor
public class SalesmanController {

    private SalesmanService salesmanService;

    @GetMapping("/{id}")
    public Salesman getSalesmanById(@PathVariable UUID id){
        return salesmanService.getSalesman(id);
    }

    @PutMapping("/profile-update")
    public Salesman updateProfileDate(@RequestBody Salesman salesman){
        return salesmanService.updateProfileData(salesman);
    }

    @DeleteMapping("/delete-profile")
    public @ResponseBody ResponseEntity<String> deleteSalesmanProfile(@RequestBody Salesman salesman){
        salesmanService.deleteProfile(salesman);
        System.out.println(salesman.getUsername());
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
