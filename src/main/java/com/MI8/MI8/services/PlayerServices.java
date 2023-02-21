package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServices {

    @Autowired
    PlayerRepository playerRepo;

    public Player createPlayerCharacter(String name) {
        Player player = new Player(name);
        playerRepo.save(player);
        return player;
    }

    public Player getCharacter(int id) {
        return playerRepo.findById(id).get();
    }

    public void deletePlayerCharacter(Player player) {
        playerRepo.delete(player);
    }

}
