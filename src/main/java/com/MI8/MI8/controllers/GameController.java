package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.ReplyDTO;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import com.MI8.MI8.services.GameServices;
import com.MI8.MI8.services.PlayerServices;
import com.MI8.MI8.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    GameServices gameServices;
    @Autowired
    PlayerServices playerServices;
    @Autowired
    RoomServices roomServices;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    GameRepository gameRepo;
    @Autowired
    RoomRepository roomRepo;

    //returns a list of all games
    @GetMapping
    public ResponseEntity<List<Game>> getGame(){
        return new ResponseEntity<>(gameRepo.findAll(),HttpStatus.FOUND);
    }

    //finds a game by its id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Game> getGameByID(@PathVariable int id){
        if (gameRepo.findById(id).isPresent()) {
            return new ResponseEntity<>(gameRepo.findById(id).get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    //creates a game which is tied to a player Id
    @PostMapping(value = "/{player_id}")
    public ResponseEntity<ReplyDTO> createNewGame(@PathVariable int player_id ){
        if (playerRepo.findById(player_id).isPresent() && !playerRepo.findById(player_id).get().isStartedGame()) {
            return new ResponseEntity(gameServices.makeNewGame(player_id), HttpStatus.CREATED);
        } else if(playerRepo.findById(player_id).get().isStartedGame()) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //deletes a game
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable int id){
        if (gameRepo.findById(id).isPresent()){
            gameRepo.deleteById(id);
            return new ResponseEntity<>("Game with "+ id + " deleted.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Game not found",HttpStatus.NOT_FOUND);
        }
    }

    //progress game - go to next room
    @PatchMapping(value = "/{id}/{room}")
    public ResponseEntity<ReplyDTO> moveRoom(@PathVariable int id,
                                         @PathVariable String room){
        if (roomServices.canMoveToRoom(id,room)){
            return gameServices.enterRoom(id,room);
        } else {
            return new ResponseEntity<>(new ReplyDTO("You cannot move there"), HttpStatus.BAD_REQUEST);
        }
    }

}