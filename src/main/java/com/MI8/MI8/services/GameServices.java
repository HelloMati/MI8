package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServices {

    @Autowired
    GameRepository gameRepo;

    @Autowired
    PlayerCharacterRepository playerRepo;

    public Game makeNewGame(int player_id){
        Game game = new Game();
        game.setCharacter(playerRepo.findById(player_id).get());
        gameRepo.save(game);
        return game;
    }
}
