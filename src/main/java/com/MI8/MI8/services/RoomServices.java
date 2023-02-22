package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.Hibernate.contains;

@Service
public class RoomServices {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    GameRepository gameRepo;

    //add next rooms on
    public void addRoom(Room room, Integer roomID){
        List<Integer> nextRooms = room.getNextRooms();
        nextRooms.add(roomID);
        room.setNextRooms(nextRooms);
        roomRepository.save(room);
    }

    //check if you can move to the selected room
    public boolean canMoveToRoom(int gameId,int roomId){
        if (gameRepo.findById(gameId).isPresent()&& roomRepository.findById(roomId).isPresent()) {
            Game game = gameRepo.findById(gameId).get();
            Room roomToMoveTo = roomRepository.findById(roomId).get();
            if(game.getCurrentRoom().isLit()) {
                return game.getCurrentRoom().getNextRooms().contains(roomToMoveTo.getId());
            }
        }
        return false;
    }



}
