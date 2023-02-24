package com.MI8.MI8.services;

import com.MI8.MI8.models.*;
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

    //returns list of items for reply DTO
    public static List<String> getItemNames(Game game) {
        List<Item> inventory = game.getCharacter().getInventory();
        List<String> toReturn = new ArrayList<>();
        for (Item item : inventory) {
            toReturn.add(item.getItemName());
        }
        return toReturn;
    }

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
    public ResponseEntity<ReplyDTO> useItem(int playerId, String itemName) {
        //get player
        Player player = playerRepo.findById(playerId).orElse(null);
        //get item
        Item itemToUse = itemRepo.findByName(itemName).orElse(null);
        //get room we're in
        Room roomIn = player.getGame().getCurrentRoom();
        //check the item exists:
        if (itemToUse == null) {
            return new ResponseEntity<>(new ReplyDTO("That item doesn't exist"), HttpStatus.NOT_FOUND);
        }
        //check the player has the item:
        if (!player.getInventory().contains(itemToUse)) {
            return new ResponseEntity<>(new ReplyDTO("You don't have that item"), HttpStatus.OK);
        }
        //check the item can be used here:
        if (!itemToUse.getRoomCanBeUsedIn().contains(roomIn)) {
            return new ResponseEntity<>(new ReplyDTO("You can't think of a useful way to use that here"), HttpStatus.METHOD_NOT_ALLOWED);
        }
        ReplyDTO reply = new ReplyDTO();
        reply.setInventory(ItemService.getItemNames(player.getGame()));
        reply.setRoomsYouCanEnter(roomIn.getNextRooms());
        switch (itemName) {
            case "torch":
                //light up room
                roomIn.setLit(true);
                roomRepo.save(roomIn);
                //drop torch
                reply.setReply("You light up the room you are in, you can now see!");
                return new ResponseEntity<>(reply, HttpStatus.OK);
            case "eyes":
                //scans room
                if (!roomIn.isLit()) {
                    reply.setReply("It is too dark, you cannot see");
                    return new ResponseEntity<>(reply, HttpStatus.OK);
                }
                if (roomIn.getId() == 1) { //if in plaza add route to basement
                    roomServices.addRoom(roomIn, "basement");
                } else if (roomIn.getRoomName().equals("elevator")) {
                    roomServices.addRoom(roomIn, "basement");
                } else if (roomIn.getId() == 2) { //if in basement get multitool
                    playerServices.updateInventory(playerId, "multiTool", true);
                } else if (roomIn.getId() == 6) { //if in security room get the keycard
                    playerServices.updateInventory(playerId, "keycard", true);
                } else if (roomIn.getId() == 8) { //if in ceo's office get laptop
                    playerServices.updateInventory(playerId, "laptop", true);
                } else if (roomIn.getRoomName().equals("vault")) {
                    playerServices.updateInventory(playerId, "tracker", true);
                }
                reply.setInventory(ItemService.getItemNames(player.getGame()));
                reply.setReply(roomIn.getSearchRoomMessage());
                return new ResponseEntity<>(reply, HttpStatus.OK);
            case "multiTool":
                //check the room is lit to use
                if (roomIn.isLit()) {
                    //remove screws of vent - adds link from basement to vents
                    roomServices.addRoom(roomIn, "airvents");
                    roomRepo.save(roomIn);
                    //remove key
                    playerServices.updateInventory(playerId, "multiTool", false);
                    reply.setRoomsYouCanEnter(roomIn.getNextRooms());
                    reply.setReply("You use the MultiTool to remove the vent cover, you can now squeeze into the vents");
                    return new ResponseEntity<>(reply, HttpStatus.OK);
                } else {
                    reply.setReply("It is too dark you cannot see");
                    return new ResponseEntity<>(reply, HttpStatus.OK);
                }
            case "keycard":
                roomServices.addRoom(roomIn, "ceosoffice");
                reply.setReply("You insert the keycard and the button for the CEO's office lights up.");
                return new ResponseEntity<>(reply, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ReplyDTO("error"), HttpStatus.BAD_REQUEST);
    }
}
