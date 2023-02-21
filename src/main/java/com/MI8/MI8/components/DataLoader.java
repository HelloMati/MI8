package com.MI8.MI8.components;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.ItemRepository;
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
    ItemRepository itemRepo;

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

        room elevator = new Room("You've made it to the elevator agent " +name+ ". It seems the new owners have denied access to the basement floor. Should you choose you can access it via an old and discreet hatch panel above you. Our previous operatives have "+
                "ensured this hatch will be loose enough for your access. Alternatively you can access the Security station or head straight for the CEO's Office. choose wisely agent.");
        room airvents;
        room security = new Room("Your disguise has enabled you to get this far however be aware agent, you must act swiftly. The security personnel will be on the lookout. We have instigated a scandal on the lower floors to detract their attention." +
                "retrieve one of the spare keycards and make it quick.");
        room valut= new Room();
        room ceosOffice;
        room ExtractionPoint;

        //adding path to the plaza
        roomServices.addRoom(plaza,2);
        roomServices.addRoom(plaza,3);
        roomRepo.save(plaza);


        //items
        Item torch = new Item("This is a torch, you can use it to light up the room","Common");
        itemRepo.save(torch);

        Item keycard = new Item("This keycard allows you to gain access to the CeosOffice.","rare");



    }
}
