package com.MI8.MI8.services;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.ItemRepository;
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
    ItemRepository itemRepo;
    @Autowired
    PlayerServices playerServices;

    //add room an item can be used in
    public void addRoom(Item item, Room room){
        ArrayList<Room> nextRooms = new ArrayList<>(item.getRoomCanBeUsedIn());
        nextRooms.add(room);
        item.setRoomCanBeUsedIn(nextRooms);
    }

    //use item
    //switch to check what the item is
    //check can be used in the room
    //check item is in the player's inventory
    //do the thing for each item
        //use the item
        //remove the item
    public Room useItem(int roomId, String name,int playerId){
        //retrieve item
        Item itemToUse = itemRepo.findByName(name).orElse(null);
        //retrieve room we're in
        Room roomIn = roomRepo.findById(roomId).orElse(null);
        //can item be used in room
        boolean canItemBeUsed = itemToUse.getRoomCanBeUsedIn().contains(roomIn);
        //check player has item
        if (itemToUse != null && roomIn != null && canItemBeUsed) {
            switch (name) {
                case "torch":
                    //light up room
                    roomIn.setLit(true);
                    roomRepo.save(roomIn);
                    //drop torch
                    playerServices.updateInventory(playerId,itemToUse.getId(),false);
                    return roomIn;
                case "key":
                    //open a door
                    //remove key

            }
        }
    return null;
    }
}
