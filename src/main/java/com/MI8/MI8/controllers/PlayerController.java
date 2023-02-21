package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    PlayerServices playerService;

    @PostMapping
    public ResponseEntity<Player> createNewPlayer(@RequestParam String name) {
        Player player = playerService.createPlayerCharacter(name);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getCharacter(@PathVariable int id) {
        return new ResponseEntity<>(playerService.getCharacter(id), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //handle items
    //add an item true for add, false for remove
    @PutMapping(value = "/{id}")
    public ResponseEntity<Player> pickUpItem(@PathVariable int id,
                                       @RequestParam int itemId,
                                       @RequestParam Boolean addOrRemove){
        Player player = playerService.updateInventory(id,itemId,addOrRemove);
        return player != null ? new ResponseEntity<>(player,HttpStatus.OK):
                    new ResponseEntity<>(player,HttpStatus.NOT_FOUND);
    }
}