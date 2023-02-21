package com.MI8.MI8.services;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Player updateInventory(int playerId,int itemId,boolean addOrRemove){
        Player player = playerRepo.findById(playerId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);
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