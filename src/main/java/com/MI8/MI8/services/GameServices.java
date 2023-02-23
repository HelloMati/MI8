package com.MI8.MI8.services;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.ReplyDTO;
import com.MI8.MI8.models.Room;
import com.MI8.MI8.repositories.GameRepository;
import com.MI8.MI8.repositories.ItemRepository;
import com.MI8.MI8.repositories.PlayerRepository;
import com.MI8.MI8.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameServices {

    @Autowired
    GameRepository gameRepo;
    @Autowired
    PlayerRepository playerRepo;
    @Autowired
    RoomRepository roomRepo;
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    WinConditionServices winConditionServices;


    //makes a new game
    public ReplyDTO makeNewGame(int player_id){
        Game game = new Game();
        Player player = playerRepo.findById(player_id).get();
        game.setCurrentRoom(roomRepo.findById(1).get());
        game.setCharacter(player);
        player.setGame(game);
        player.setStartedGame(true);
        game.getCurrentRoom().setHaveEnteredRoom(true);
        gameRepo.save(game);
        playerRepo.save(player);
        ReplyDTO reply = new ReplyDTO(game.getCurrentRoom().getFirstEntranceMessage(),game.getCurrentRoom().getNextRooms(),itemService.getItemNames(game));
        return reply;
    }

    //updates room for a game
    public ResponseEntity<ReplyDTO> enterRoom(int gameId, String room){
        Game currentGame = gameRepo.findById(gameId).get();
        Room roomEntering = roomRepo.findByRoomName(room).get();
        currentGame.setCurrentRoom(roomEntering);
        gameRepo.save(currentGame);
        winConditionServices.winningCondition(currentGame);
        if (currentGame.isPlayerHasWon()){
            ReplyDTO winReply = new ReplyDTO("Well done! You have successfully managed to cripple Specter's growing criminal influence across the globe, whilst they remain at large," +
                    " your accomplishments will get us one step closer to stopping their nefarious affairs. you'll be" +
                    "returning to MI8 HQ for your next briefing agent.");
            return new ResponseEntity<>(winReply, HttpStatus.OK);
        }
        ReplyDTO reply = new ReplyDTO("",roomEntering.getNextRooms(),itemService.getItemNames(currentGame));
        if (roomEntering.getHaveEnteredRoom()){
            reply.setReply(roomEntering.getRoomDescription());
            return new ResponseEntity<>(reply, HttpStatus.OK);

        } else {
            roomEntering.setHaveEnteredRoom(true);
            roomRepo.save(roomEntering);
            reply.setReply(roomEntering.getFirstEntranceMessage());
            return new ResponseEntity<>(reply, HttpStatus.OK);
        }
    }

}
