package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.services.ItemService;
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
    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseEntity<Player> createNewPlayer(@RequestParam String name) {
        Player player = playerService.createPlayerCharacter(name);
        playerService.updateInventory(player.getId(), "eyes", true);
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getCharacter(@PathVariable int id) {
        return new ResponseEntity<>(playerService.getCharacter(id), HttpStatus.OK);
    }
//I don't think we really need this one
//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player player) {
//        Player updatedPlayer = playerService.updatePlayer(id, player);
//        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
//    }

    //Use item seems more logical coming from the player
    @PatchMapping(value= "/{playerId}")
    public ResponseEntity<String> useItem(@PathVariable int playerId,
                                        @RequestParam String itemName){
        return itemService.useItem(playerId,itemName);
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
                                       @RequestParam String itemName,
                                       @RequestParam Boolean addOrRemove){
        Player player = playerService.updateInventory(id,itemName,addOrRemove);
        return player != null ? new ResponseEntity<>(player,HttpStatus.OK):
                    new ResponseEntity<>(player,HttpStatus.NOT_FOUND);
    }
}