package com.MI8.MI8.controllers;

import com.MI8.MI8.models.Room;
import com.MI8.MI8.services.ItemService;
import com.MI8.MI8.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomServices roomServices;

    @Autowired
    ItemService itemService;

    @PatchMapping(value="/{id}/{player_id}")
    public ResponseEntity<Room> useItem(@PathVariable int id,
                                        @PathVariable int player_id,
                                        @RequestParam String itemName){
        Room room = itemService.useItem(id,itemName,player_id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}
