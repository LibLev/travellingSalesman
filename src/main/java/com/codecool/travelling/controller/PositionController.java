package com.codecool.travelling.controller;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search/{city}/{positionName}")
    public List<Position> findAllPositionByCityAndPositionType(@PathVariable String city, @PathVariable String positionName){
        return positionService.findAllPositionByCityAndPositionType(city, positionName);
    }
}
