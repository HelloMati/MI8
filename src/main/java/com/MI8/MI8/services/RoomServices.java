package com.MI8.MI8.services;

import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServices {

    @Autowired
    RoomRepository roomRepository;

    //add next rooms on
    public void addRoom(Room room, Integer roomID){
        List<Integer> nextRooms = room.getNextRooms();
        nextRooms.add(roomID);
        room.setNextRooms(nextRooms);
    }



}
