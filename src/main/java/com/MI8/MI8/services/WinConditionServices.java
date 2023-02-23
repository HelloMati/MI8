package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Item;
import com.MI8.MI8.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinConditionServices {

    @Autowired
    ItemRepository itemRepository;

    public void winningCondition (Game game){
        Item laptop = itemRepository.findByName("laptop").get();
        Item tracker = itemRepository.findByName("tracker").get();
        Boolean inExtraction = game.getCurrentRoom().getRoomName().equals("extraction");
        Boolean hasWinObject = game.getCharacter().getInventory().contains(laptop) || game.getCharacter().getInventory().contains(tracker);
        if (inExtraction && hasWinObject){
            game.setPlayerHasWon(true);
        }
    }

}
