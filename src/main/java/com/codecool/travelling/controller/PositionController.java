package com.codecool.travelling.controller;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/delete-position/{id}")
    public @ResponseBody ResponseEntity<String> deletePosition(@PathVariable UUID id){
        positionService.deletePosition(id);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
