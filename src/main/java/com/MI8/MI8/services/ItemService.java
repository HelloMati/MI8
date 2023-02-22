package com.MI8.MI8.services;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    RoomRepository roomRepo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    ItemRepository itemRepo;
    @Autowired
    PlayerServices playerServices;
    @Autowired
    RoomServices roomServices;

    //add room an item can be used in
    public void addRoom(Item item, Room room) {
        List<Room> nextRooms = item.getRoomCanBeUsedIn();
        nextRooms.add(room);
        item.setRoomCanBeUsedIn(nextRooms);
    }


    //use item
    //switch to check what the item is
    //check player is in the room
    //check can be used in the room
    //check item is in the player's inventory
    //do the thing for each item
    //use the item
    //remove the item
    public ResponseEntity<String> useItem(int playerId, String itemName) {
        //get player
        Player player = playerRepo.findById(playerId).orElse(null);
        //get item
        Item itemToUse = itemRepo.findByName(itemName).orElse(null);
        //get room we're in
        Room roomIn = player.getGame().getCurrentRoom();
        //check the item exists:
        if (itemToUse == null) {
            return new ResponseEntity<>("That item doesn't exist", HttpStatus.NOT_FOUND);
        }
        //check the player has the item:
        if (!player.getInventory().contains(itemToUse)) {
            return new ResponseEntity<>("You don't have that item", HttpStatus.OK);
        }
        //check the item can be used here:
        if (!itemToUse.getRoomCanBeUsedIn().contains(roomIn)) {
            return new ResponseEntity<>("You can't think of a useful way to use that here", HttpStatus.METHOD_NOT_ALLOWED);
        }
        switch (itemName) {
            case "torch":
                //light up room
                roomIn.setLit(true);
                roomRepo.save(roomIn);
                //drop torch
//                    playerServices.updateInventory(playerId,itemToUse.getId(),false);
                return new ResponseEntity<>("You light up the room you are in, you can now see!",HttpStatus.OK);
            case "eyes":
                //scans room
                if(!roomIn.isLit()){
                    return new ResponseEntity<>("It is too dark, you cannot see",HttpStatus.OK);
                }
                if (roomIn.getId() == 1) { //if in plaza add route to basement
                    roomServices.addRoom(roomIn, "basement");
                } else if (roomIn.getId() == 2) { //if in basement get multitool
                    playerServices.updateInventory(playerId, "multiTool", true);
                } else if (roomIn.getId() == 6) { //if in security room get the keycard
                    playerServices.updateInventory(playerId, "keycard", true);
                } else if (roomIn.getId() == 8) { //if in ceo's office get laptop
                    playerServices.updateInventory(playerId, "laptop", true);
                }
                return new ResponseEntity<>(roomIn.getSearchRoomMessage(),HttpStatus.OK);
            case "multiTool":
                //check the room is lit to use
                if (roomIn.isLit()) {
                    //remove screws of vent - adds link from basement to vents
                    roomServices.addRoom(roomIn, "airvents");
                    roomRepo.save(roomIn);
                    //remove key
                    playerServices.updateInventory(playerId, "multiTool", false);
                    return new ResponseEntity<>("You use the MultiTool to remove the vent cover, you can now squeeze into the vents",HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("It is too dark you cannot see",HttpStatus.OK);
                }
            case "keycard":
                roomServices.addRoom(roomIn, "ceosoffice");
                return new ResponseEntity<>("You insert the keycard and the button for the CEO's office lights up.",HttpStatus.OK);
        }
        return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
    }
}
