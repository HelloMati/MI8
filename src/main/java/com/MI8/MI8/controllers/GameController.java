package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.services.GameServices;
import com.MI8.MI8.services.PlayerServices;
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
    PlayerRepository playerRepo;
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
    //creates a game which is tied to a player Id
    @PostMapping(value = "/{player_id}")
    public ResponseEntity<Game> createNewGame(@PathVariable int player_id ){
        if (playerRepo.findById(player_id).isPresent()) {
            Game game = gameServices.makeNewGame(player_id);
            playerRepo.findById(player_id).get().setGame(game);
            return new ResponseEntity(game, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
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

}
