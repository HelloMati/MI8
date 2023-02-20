package com.MI8.MI8.controllers;


import com.MI8.MI8.models.Player;
import com.MI8.MI8.services.PlayerServices;
import jakarta.persistence.Id;
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
    public ResponseEntity<Player> CreateNewPlayer(@RequestParam String name) {
        Player player = playerService.createPlayerCharacter(name);
        return new ResponseEntity(player, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getCharacter(@PathVariable int id){
        return new  ResponseEntity<>(playerService.getCharacter(id), HttpStatus.OK);
    }



}
