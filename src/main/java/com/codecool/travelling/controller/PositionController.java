package com.codecool.travelling.controller;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.service.CompanyService;
import com.codecool.travelling.service.PositionService;
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
public class PositionController {

    private PositionService positionService;

    @PostMapping("/add-position")
    public void createNewPosition(@RequestBody Map<String, String> data){
        positionService.createNewPosition(data);

    }
}
