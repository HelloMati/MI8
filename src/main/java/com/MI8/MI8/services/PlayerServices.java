package com.MI8.MI8.services;

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
        return playerRepo.findById(id).orElse(null);
    }

    public void deletePlayer(int id) {
        Player player = playerRepo.findById(id).orElse(null);
        if (player != null) {
            playerRepo.delete(player);
        }
    }

    public Player updatePlayer(int id, Player newPlayer) {
        Player player = playerRepo.findById(id).orElse(null);
        if (player != null) {
            player.setName(newPlayer.getName());
            playerRepo.save(player);
        }
        return player;
    }
}