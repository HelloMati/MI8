package com.MI8.MI8.controllers;


import com.MI8.MI8.models.PlayerCharacter;
import com.MI8.MI8.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    PlayerServices playerService;

    @PostMapping
    public ResponseEntity<PlayerCharacter> CreateNewPlayer(@RequestBody String name) {
        PlayerCharacter player = new PlayerCharacter(name);
        return new ResponseEntity(player, HttpStatus.CREATED);
    }

}
