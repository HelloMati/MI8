package com.MI8.MI8.components;

import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import com.MI8.MI8.services.ItemService;
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
    @Autowired
    ItemService itemService;


    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //room id 1
        Room plaza = new Room("You are droped of at the Plaza outside the building" +
                "/nIn front of you is the front door","You enter the Plaza, it seems quiet out here",
                true);
        roomRepo.save(plaza);
        //room id 2
        Room basement = new Room("You enter the basement, it's very dark in here","This is the basement",
                false);
        roomRepo.save(basement);
        //room id 3
        Room lobby = new Room("You enter the lobby there is a reception desk with a bored looking receptionist." +
                "/nBy the elevators you see some guards.","You return to the Lobby",
                true);
        roomRepo.save(lobby);
        //room id 4
        Room elevator = new Room("You've made it to the elevator agent. It seems the new owners have denied access to" +
                " the basement floor. Should you choose you can access it via an old and discreet hatch panel above you. Our previous operatives have "+
                "ensured this hatch will be loose enough for your access. Alternatively you can access the Security station or head straight " +
                "for the CEO's Office. choose wisely agent.",
                "You return to the elevator, you can choose to go to the Lobby, Security Station or the CEOs office",
                true);
        roomRepo.save(elevator);
        //room id 5
        Room airvents = new Room("You slither along the dark and narrow access vents ,avoiding any unwanted attention.",
                "This is the airvents",
                true);
        roomRepo.save(airvents);
        //room id 6
        Room security = new Room("Your disguise has enabled you to get this far however be aware agent, you must act swiftly." +
                " The security personnel will be on the lookout. We have instigated a scandal on the lower floors to detract their attention." +
                "retrieve one of the spare keycards and make it quick.",
                "You return to the security room, there is nothing left of interest here",
                true);
        roomRepo.save(security);
        //room id 7
        Room vault = new Room("Well done agent, the tracker you have placed on their criminal earnings will allow use to intercept" +
                " their operation when they least expect it, catching them red handed. we have set up an extraction point. Great work agent.",
                "You return to the vault, there is nothing left",
                true);
        roomRepo.save(vault);
        //room id 8
        Room ceosOffice = new Room("Quickly agent, access the computer and retrieve the laptop. We need the documents within as evidence of Specter's" +
                " unscrupulous and opportunistic dealings. we have set up an extraction point. Great work agent.",
                "You return to the ceo's office, looks like it won't be needed soon!",
                true);
        roomRepo.save(ceosOffice);
        //room id 9
        Room extractionPoint = new Room("You have successfully managed to cripple Specters growing criminal influence across the globe, whilst they remain at large," +
                " your accomplishments will get us one step closer to stopping their nefarious affairs. youll be" +
                "returning to MI8 HQ for your next briefing agent.","",
                true);

        //adding path to the plaza
        roomServices.addRoom(plaza,2);
        roomServices.addRoom(plaza,3);
        roomRepo.save(plaza);

        //adding path to the basement
        roomServices.addRoom(basement,5);
        roomRepo.save(basement);

        //adding path to the Lobby
        roomServices.addRoom(lobby,4);
        roomRepo.save(lobby);

        //adding path to the Elevator
        roomServices.addRoom(elevator,2);
        roomServices.addRoom(elevator,6);
        roomServices.addRoom(elevator,8);
        roomRepo.save(elevator);

        //adding path to the airvents
        roomServices.addRoom(airvents,7);
        roomRepo.save(airvents);

        //adding path to the vaults
        roomServices.addRoom(extractionPoint,9);
        roomRepo.save(vault);

        //adding path to the security
        roomServices.addRoom(security,4);
        roomRepo.save(security);

        //adding path to the ceosOffice
        roomServices.addRoom(extractionPoint,9);
        roomServices.addRoom(security,4);
        roomRepo.save(ceosOffice);



        //items
        Item torch = new Item("torch","This is a torch, you can use it to light up the room","Common");
        itemService.addRoom(torch,basement);
        itemService.addRoom(torch,airvents);
        itemRepo.save(torch);

        Item multiTool = new Item("multiTool", "This is a Multi-Tool, you can use it to remove screws","Common");
        itemRepo.save(multiTool);

        Item keycard = new Item("keycard","This keycard allows you to gain access to the CeosOffice.","rare");
        itemRepo.save(keycard);

        Item laptop = new Item ("laptop","Contains evidence of Specters villainous international operations.", "superRare");
        itemRepo.save(laptop);

        Item eyes = new Item("eyes","these are your eyes, you ues them to look around.","Common");
        itemService.addRoom(eyes,plaza);
        itemService.addRoom(eyes,basement);
        itemService.addRoom(eyes,airvents);



    }
}
