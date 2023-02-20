package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
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

    public Game makeNewGame(int player_id){
        Game game = new Game();
        game.setCurrentRoom(roomRepo.findById(1).get());
        game.setCharacter(playerRepo.findById(player_id).get());
        gameRepo.save(game);
        return game;
    }
}
