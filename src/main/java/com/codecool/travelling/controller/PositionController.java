package com.codecool.travelling.controller;

import com.codecool.travelling.model.Position;
import com.codecool.travelling.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/search/{city}/{positionName}")
    public List<Position> findAllPositionByCityAndPositionType(@PathVariable String city, @PathVariable String positionName){
        return positionService.findAllPositionByCityAndPositionType(city, positionName);
    }

    @GetMapping("/get-position/{id}")
    public Position getPosition(@PathVariable UUID id){
        return positionService.getPositionById(id);
    }

    @PutMapping("/update-position")
    public Position updatePositionDetails(@RequestBody Position position){
        return positionService.updatePositionDetails(position);
    }

    @DeleteMapping("/delete-position")
    public @ResponseBody ResponseEntity<String> deletePosition(@RequestBody Position position){
        positionService.deletePosition(position);
        return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
}
