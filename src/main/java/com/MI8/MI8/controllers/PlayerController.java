package com.MI8.MI8.controllers;


import com.MI8.MI8.models.Player;
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
    public ResponseEntity<Player> CreateNewPlayer(@RequestParam String name) {
        Player player = playerService.createPlayerCharacter(name);
        return new ResponseEntity(player, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getCharacter(@PathVariable int id){
        return new  ResponseEntity<>(playerService.getCharacter(id), HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Player> getPlayerId(@PathVariable int id){
//        Player player = playerService.getPlayerId(id);
//        return new ResponseEntity<>(player, HttpStatus.OK);
//    }
//
//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestParam Player player){
//        Player updatedPlayer = playerService.updatePlayer(id, player);
//        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity deletePlayer(@PathVariable int id){
//        playerService.deletePlayer(id);
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//    }
}