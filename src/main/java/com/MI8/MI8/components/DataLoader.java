package com.MI8.MI8.components;

import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerCharacterRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerCharacterRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Room firstRoom = new Room("This is an empty room");
        roomRepo.save(firstRoom);

    }
}