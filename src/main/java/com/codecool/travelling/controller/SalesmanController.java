package com.codecool.travelling.controller;

import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.service.SalesmanService;
import lombok.AllArgsConstructor;
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
}
