package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
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

    @Autowired
    PlayerRepository playerRepository;

    //add next rooms on
    public void addRoom(Room room, String roomName){
        List<String> nextRooms = room.getNextRooms();
        nextRooms.add(roomName);
        room.setNextRooms(nextRooms);
        roomRepository.save(room);
    }

    //check if you can move to the selected room
    public boolean canMoveToRoom(int gameId,String room){
        //check game and room exist
        if (gameRepo.findById(gameId).isPresent()&& roomRepository.findByRoomName(room).isPresent()) {
            Game game = gameRepo.findById(gameId).get();
            if(game.getCurrentRoom().isLit()) {
                return game.getCurrentRoom().getNextRooms().contains(room);
            }
        }
        return false;
    }

}
