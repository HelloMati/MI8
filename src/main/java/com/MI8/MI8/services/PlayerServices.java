package com.MI8.MI8.services;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServices {

    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    ItemRepository itemRepo;

    public Player createPlayerCharacter(String name) {
        Player player = new Player(name);
        playerRepo.save(player);
        return player;
    }

    public ResponseEntity<Player> getCharacter(int id) {
        Player player = playerRepo.findById(id).orElse(null);
        if(player == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(player,HttpStatus.FOUND);
        }
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

    public Player updateInventory(int playerId,String itemId,boolean addOrRemove){
        Player player = playerRepo.findById(playerId).orElse(null);
        Item item = itemRepo.findByName(itemId).orElse(null);
        if(item != null && player != null && addOrRemove){
            List<Item> inventory = player.getInventory();
            inventory.add(item);
            player.setInventory(inventory);
            playerRepo.save(player);
            return player;
        } else if (item != null && player != null && !addOrRemove) {
            List<Item> inventory = player.getInventory();
            inventory.remove(item);
            player.setInventory(inventory);
            playerRepo.save(player);
            return player;
        }
        return null;
    }
}