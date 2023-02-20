package com.MI8.MI8.services;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServices {

    @Autowired
    PlayerRepository playerCharacterRepository;

    public Player createPlayerCharacter(String name) {
        Player player = new Player(name);
        playerCharacterRepository.save(player);
        return player;
    }

    public Player getCharacter(int id){
        return playerCharacterRepository.findById(id).get();
    }

}
