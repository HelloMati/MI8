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
        Room plaza = new Room("plaza",
                "You are dropped off at the Plaza outside the building. \nIn front of you is the front door",
                "You enter the Plaza, it seems quiet out here",
                true,
                "You scope out around the Plaza and find a hatch which appears to lead down into the basement.");
        roomRepo.save(plaza);
        //room id 2
        Room basement = new Room("basement",
                "You enter the basement, it's very dark in here",
                "This is the basement",
                false,
                "You quickly search the basement and find a multi-tool in a nearby toolbox, on the wall up high is a " +
                        "grate leading to the building's air-conditioning system.");
        roomRepo.save(basement);
        //room id 3
        Room lobby = new Room("lobby",
                "You enter the lobby there is a reception desk with a bored looking receptionist." +
                "\nBy the elevators you see some guards.",
                "You return to the Lobby, nothing has changed in here",
                true,
                "You mill around in the lobby, you see the occasional business person come and go, but most have gone home for the night.");
        roomRepo.save(lobby);
        //room id 4
        Room elevator = new Room("elevator",
                "You've made it to the elevator agent. It seems the new owners have denied access to" +
                " the basement floor. Should you choose you can access it via an old and discreet hatch panel above you. Our previous operatives have "+
                "ensured this hatch will be loose enough for your access. Alternatively, you can access the Security station or head straight " +
                "for the CEO's Office. choose wisely agent.",
                "You return to the elevator, you can choose to go to the Lobby, Security Station or the CEO's office",
                true,
                "The calming decor and soothing music doesn't seem to do much for your nerves agent, you see nothing of use besides the floor numbers." +
                "It would be wise to either use the elevator hatch, or proceed to the Security Station, or CEO's office.");
        roomRepo.save(elevator);
        //room id 5
        Room airvents = new Room("airvents",
                "You slither along the dark and narrow access vents ,avoiding any unwanted attention.",
                "These are the airvents",
                true,
                "You navigate the vents and narrowly avoid a deadly fall into the large industrial fans. You keep your cool. You overhear hints and suggestions of " +
                        "further plotting ahead from some of the night-crew.");
        roomRepo.save(airvents);
        //room id 6
        Room security = new Room("security",
                "Your disguise has enabled you to get this far however be aware agent, you must act swiftly." +
                " The security personnel will be on the lookout. We have instigated a scandal on the lower floors to detract their attention." +
                "Retrieve one of the spare keycards and make it quick.",
                "You return to the Security room, there is nothing left of interest here",
                true,
                "You retrieve the keycard as the guards quickly rush down to sort out all of the commotion on the ground floor.");
        roomRepo.save(security);
        //room id 7
        Room vault = new Room("vault",
                "Well done agent, the tracker you have placed on their criminal earnings will allow use to intercept" +
                " their operation when they least expect it, catching them red handed. We have set up an extraction point. Great work agent.",
                "You return to the vault, there is nothing left",
                true,
                "You scope out the vault and notice an escape route");
        roomRepo.save(vault);
        //room id 8
        Room ceosOffice = new Room("ceosoffice",
                "Quickly agent, access the computer and retrieve the laptop. We need the documents within as evidence of Specter's" +
                " unscrupulous and opportunistic dealings. we have set up an extraction point. Great work agent.",
                "You return to the CEO's office. There is a gold statue and large painting of the CEO behind a grand desk. This looks rather extravagant.",
                true,
                "You spot the computer on the CEO's desk. You access top secret files that will take down Spectre's criminal enterprise and take the laptop with you");
        roomRepo.save(ceosOffice);
        //room id 9
        Room extraction = new Room("extraction",
                "Get back in there agent. Your mission is not complete",
                "This is the roof top of the Plaza. It has a helicopter pad",
                true,
                "You hear the helicopter which arrives to airlift you back to MI8 HQ");

        //adding paths from the plaza
        roomServices.addRoom(plaza,"lobby");
        roomRepo.save(plaza);

        //adding path from the basement
        roomServices.addRoom(basement,"plaza");
        roomRepo.save(basement);

        //adding path from the Lobby
        roomServices.addRoom(lobby,"elevator");
        roomServices.addRoom(lobby,"plaza");
        roomRepo.save(lobby);

        //adding path from the Elevator
        roomServices.addRoom(elevator,"security");
        roomRepo.save(elevator);

        //adding path from the airvents
        roomServices.addRoom(airvents,"vault");
        roomRepo.save(airvents);

        //adding path from the vaults
        roomServices.addRoom(vault,"extraction");
        roomRepo.save(vault);

        //adding path from the security
        roomServices.addRoom(security,"elevator");
        roomRepo.save(security);

        //adding path from the ceosOffice
        roomServices.addRoom(ceosOffice,"extraction");
        roomRepo.save(ceosOffice);

        //adding path from extraction
        roomServices.addRoom(extraction,"ceosoffice");
        roomServices.addRoom(extraction,"vault");
        roomRepo.save(ceosOffice);



        //items
        //item id 1
        Item torch = new Item("torch","This is a torch, you can use it to light up the room","Common");
        itemService.addRoom(torch,basement);
        itemService.addRoom(torch,airvents);
        itemRepo.save(torch);

        //item id 2
        Item multiTool = new Item("multiTool", "This is a Multi-Tool, you can use it to remove screws","Common");
        itemService.addRoom(multiTool,basement);
        itemRepo.save(multiTool);

        //item id 3
        Item keycard = new Item("keycard","This keycard allows you to gain access to the CeosOffice.","rare");
        itemService.addRoom(keycard,ceosOffice);
        itemService.addRoom(keycard,elevator);
        itemRepo.save(keycard);

        //item id 4
        Item laptop = new Item ("laptop","Contains evidence of Specters villainous international operations.", "superRare");
        itemRepo.save(laptop);

        //item id 5
        Item eyes = new Item("eyes","these are your eyes, you ues them to look around.","Common");
        itemService.addRoom(eyes,plaza);
        itemService.addRoom(eyes,basement);
        itemService.addRoom(eyes,lobby);
        itemService.addRoom(eyes,elevator);
        itemService.addRoom(eyes,airvents);
        itemService.addRoom(eyes,security);
        itemService.addRoom(eyes,vault);
        itemService.addRoom(eyes,ceosOffice);
        itemService.addRoom(eyes,extraction);
        itemRepo.save(eyes);

        //item id 6
        Item tracker = new Item("tracker","This is the key to the tracker you hid in the vault","superRare");
        itemRepo.save(tracker);



    }
}
