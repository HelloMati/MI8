package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameServices {

    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;

    //makes a new game
    public String makeNewGame(int player_id){
        Game game = new Game();
        Player player = playerRepo.findById(player_id).get();
        game.setCurrentRoom(roomRepo.findById(1).get());
        game.setCharacter(player);
        player.setGame(game);
        player.setStartedGame(true);
        game.getCurrentRoom().setHaveEnteredRoom(true);
        gameRepo.save(game);
        playerRepo.save(player);
        return game.getCurrentRoom().getFirstEntranceMessage();
    }

    //updates room for a game
    public ResponseEntity<String> enterRoom(int gameId, String room){
        Game currentGame = gameRepo.findById(gameId).get();
        Room roomEntering = roomRepo.findByRoomName(room).get();
        currentGame.setCurrentRoom(roomEntering);
        gameRepo.save(currentGame);
        if (roomEntering.getHaveEnteredRoom()){
            return new ResponseEntity<>(roomEntering.getRoomDescription(), HttpStatus.OK);
        } else {
            roomEntering.setHaveEnteredRoom(true);
            roomRepo.save(roomEntering);
            return new ResponseEntity<>(roomEntering.getFirstEntranceMessage(), HttpStatus.OK);
        }
    }
}
