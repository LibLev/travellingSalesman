package com.codecool.travelling.controller;

import com.codecool.travelling.model.MATCH_LEVEL;
import com.codecool.travelling.model.Personality;
import com.codecool.travelling.service.PersonalityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;


@RequestMapping("/personality")
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class PersonalityController {
    private PersonalityService personalityService;

    @GetMapping("/match-Person-To-Role")
    public Optional<MATCH_LEVEL> matchPersonToRole (Personality salesPersonality){
        return personalityService.matchPersonToRole(salesPersonality);
    }



}
