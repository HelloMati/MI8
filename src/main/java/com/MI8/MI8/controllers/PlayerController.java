package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.ReplyDTO;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.services.ItemService;
import com.MI8.MI8.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    PlayerServices playerService;
    @Autowired
    ItemService itemService;

    @Autowired
    PlayerRepository playerRepo;

    @GetMapping
    public ResponseEntity<List<Player>> getAllCharacters(){
        return new ResponseEntity<>(playerRepo.findAll(),HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getCharacter(@PathVariable int id) {
        return playerService.getCharacter(id);
    }

    @PostMapping(value = "/{name}")
    public ResponseEntity<String> createNewPlayer(@PathVariable String name) {
        Player player = playerService.createPlayerCharacter(name);
        playerService.updateInventory(player.getId(), "torch", true);
        playerService.updateInventory(player.getId(), "eyes", true);
        return new ResponseEntity<>("Good evening agent " + player.getName() + ". Your destination will be in Singapore," +
                " within the inonic Marina Bay Sands. Your target is Specter, the infamous global crime syndicate. We have" +
                " reason to believe they have taken up residency in the building and your mission objective is to cripple" +
                " their operations. You will be dropped outside the building and will have to make your own way in and figure out" +
                " a way to bring them down. Good luck agent " + player.getName() + ", we are counting on you.", HttpStatus.CREATED);
    }


    //Use item seems more logical coming from the player
    @PatchMapping(value = "/{playerId}/{itemName}")
    public ResponseEntity<ReplyDTO> useItem(@PathVariable int playerId,
                                            @PathVariable String itemName) {
        return itemService.useItem(playerId, itemName);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //handle items
    //add an item true for add, false for remove
    @PutMapping(value = "/{id}/{addOrRemove}/{itemName}")
    public ResponseEntity<Player> pickUpItem(@PathVariable int id,
                                             @PathVariable Boolean addOrRemove,
                                             @PathVariable String itemName) {
        Player player = playerService.updateInventory(id, itemName, addOrRemove);
        return player != null ? new ResponseEntity<>(player, HttpStatus.OK) :
                new ResponseEntity<>(player, HttpStatus.NOT_FOUND);
    }
}