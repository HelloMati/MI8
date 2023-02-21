package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Game makeNewGame(int player_id){
        Game game = new Game();
        Player player = playerRepo.findById(player_id).get();
        game.setCurrentRoom(roomRepo.findById(1).get());
        game.setCharacter(player);
        player.setGame(game);
        player.setStartedGame(true);
        gameRepo.save(game);
        playerRepo.save(player);
        return game;
    }

    //updates room for a game
    public Room updateRoom(int gameId,int roomId){
        Game gameToUpdate = gameRepo.findById(gameId).get();
        Room roomToUpdate = roomRepo.findById(roomId).get();
        gameToUpdate.setCurrentRoom(roomToUpdate);
        gameRepo.save(gameToUpdate);
        return roomToUpdate;
    }
}
