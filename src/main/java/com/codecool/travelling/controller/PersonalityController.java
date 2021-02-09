package com.codecool.travelling.controller;

import com.codecool.travelling.service.PersonalityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
public class PersonalityController {
    private PersonalityService personalityService;



}
