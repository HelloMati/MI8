package com.MI8.MI8.services;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addRoom(Item item, Room room){
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
    public Room useItem(int roomId, String name,int playerId) {
        //get player
        Player player = playerRepo.findById(playerId).orElse(null);
        //retrieve item
        Item itemToUse = itemRepo.findByName(name).orElse(null);
        //get room we want to use it in
        Room room = roomRepo.findById(roomId).orElse(null);
        //retrieve room we're in
        Room roomIn = player.getGame().getCurrentRoom();
        //what we have to check
        //does the room exist?
        //does the item exist?
        //does the player exist?
        //are we in the right room?
        boolean areWeInTheRightRoom = (room == roomIn);
        //can item be used in room?
        boolean canItemBeUsed = itemToUse.getRoomCanBeUsedIn().contains(roomIn);
        //check player has item
        boolean playerHasItem = player.getInventory().contains(itemToUse);
        if (itemToUse != null && roomIn != null && player != null && canItemBeUsed && areWeInTheRightRoom && playerHasItem) {
            switch (name) {
                case "torch":
                    //light up room
                    roomIn.setLit(true);
                    roomRepo.save(roomIn);
                    //drop torch
//                    playerServices.updateInventory(playerId,itemToUse.getId(),false);
                    return roomIn;

                case "key":
                    //open a door
                    //remove key
                case "eyes":
                    //scans room
                    if (roomIn.getId() == 1) {
                        roomServices.addRoom(roomIn, 2);
                    } else if (roomIn.getId() == 2) {
                        playerServices.updateInventory(playerId, itemRepo.findIdByName("multiTool").get(), true);
                    } else if (roomIn.getId() == 6) {
                        playerServices.updateInventory(playerId, itemRepo.findIdByName("keycard").get(), true);
                    } else if (roomIn.getId() == 8) {
                        playerServices.updateInventory(playerId, itemRepo.findIdByName("laptop").get(), true);

                    }
                    return roomIn;

                case "multiTool":
                    //check the room is lit to use
                    if (roomIn.isLit()) {
                        //remove screws of vent - adds link from basement to vents
                        roomServices.addRoom(roomIn, 5);
                        roomRepo.save(roomIn);
                        //remove key
                        playerServices.updateInventory(playerId, itemToUse.getId(), false);
                        return roomIn;
                    } else {
                        return null;
                    }
                case "keycard":
                    roomServices.addRoom(roomIn, 8);
                    return roomIn;

            }

        }
        return null;

    }
}
