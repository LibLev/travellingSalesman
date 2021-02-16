package com.codecool.travelling.controller;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Position;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.repository.SalesmanRepository;
import com.codecool.travelling.service.PersonalityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;



@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/personality")
public class PersonalityController {

    private PersonalityService personalityService;
    private SalesmanRepository salesmanRepository;

    @PostMapping("/add-personality-to-salesman")
    public Personality setPersonalityToSalesman(@RequestBody Map<String, String> data){
        return personalityService.setPersonalityToSalesman(data);
    }

    @GetMapping("/match-person-to-role/{id}")
    public MATCH_LEVEL matchPersonToRole (@PathVariable UUID id){
        return personalityService.matchPersonToRole(salesmanRepository.findById(id).get());
    }

    @GetMapping("/get-all-matching-position/{id}")
    public List<Position> getAllMatchingPosition(@PathVariable UUID id){
        return personalityService.matchPersonToPositionsBasedOnPersonality(salesmanRepository.findById(id).get());
    }

}
