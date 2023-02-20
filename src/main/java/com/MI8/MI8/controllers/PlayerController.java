package com.MI8.MI8.controllers;


import com.MI8.MI8.models.PlayerCharacter;
import com.MI8.MI8.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    PlayerServices playerService;

    @PostMapping
    public ResponseEntity<PlayerCharacter> CreateNewPlayer(@RequestParam String name) {
        PlayerCharacter player = playerService.createPlayerCharacter(name);
        return new ResponseEntity(player, HttpStatus.CREATED);
    }

}
