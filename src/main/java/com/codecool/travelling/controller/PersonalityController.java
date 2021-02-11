package com.codecool.travelling.controller;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Personality;
import com.codecool.travelling.model.Salesman;
import com.codecool.travelling.service.PersonalityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequestMapping("/personality")
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class PersonalityController {
    private PersonalityService personalityService;

    @GetMapping("/match-Person-To-Role")
    public MATCH_LEVEL matchPersonToRole (@RequestBody Salesman salesman){
        return personalityService.matchPersonToRole(salesman);
    }



}
