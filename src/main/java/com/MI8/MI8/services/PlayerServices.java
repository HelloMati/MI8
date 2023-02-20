package com.MI8.MI8.services;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.repositories.PlayerCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServices {

    @Autowired
    PlayerCharacterRepository playerCharacterRepository;

    public Player createPlayerCharacter(String name) {
        Player player = new Player(name);
        playerCharacterRepository.save(player);
        return player;
    }

    public PlayerCharacter getCharacter(int id){
        return playerCharacterRepository.findById(id).get();
    }

    public void deletePlayerCharacter(Player player) {
        playerCharacterRepository.delete(player);
    }

}
