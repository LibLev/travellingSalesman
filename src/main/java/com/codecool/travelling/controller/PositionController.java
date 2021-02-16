package com.codecool.travelling.controller;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/position")
@AllArgsConstructor
public class PositionController {

    private PositionService positionService;

    @PostMapping("/add-position")
    public void createNewPosition(@RequestBody Position data){
        positionService.createNewPosition(data);
    }
}
