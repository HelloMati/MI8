package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerCharacterRepository;
import com.MI8.MI8.services.GameServices;
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
    PlayerCharacterRepository playerRepo;

    @Autowired
    GameRepository gameRepo;

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
    @PostMapping(value = "/{player_id}")
    public ResponseEntity<Game> createNewGame(@PathVariable int player_id ){
        if (playerRepo.findById(player_id).isPresent()) {
            Game game = gameServices.makeNewGame(player_id);
            return new ResponseEntity(game, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

}
