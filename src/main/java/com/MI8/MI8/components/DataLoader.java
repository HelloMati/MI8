package com.MI8.MI8.components;

import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import com.MI8.MI8.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;
    @Autowired
    RoomServices roomServices;


    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //room id 1
        Room plaza = new Room("You are droped of at the Plaza outside the building" +
                "/nIn front of you is the front door");
        roomRepo.save(plaza);
        //room id 2
        Room basement = new Room("You enter the basement, it's very dark in here");
        roomRepo.save(basement);
        //room id 3
        Room lobby = new Room("You enter the lobby there is a reception desk with a bored looking receptionist." +
                "/nBy the elevators you see some guards.");
        roomRepo.save(lobby);
        roomServices.addRoom(plaza,2);
        roomServices.addRoom(plaza,3);
        roomRepo.save(plaza);

    }
}
