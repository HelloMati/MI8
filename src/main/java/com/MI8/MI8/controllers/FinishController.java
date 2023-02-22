package com.MI8.MI8.controllers;

import com.MI8.MI8.services.GameServices;
import com.MI8.MI8.services.PlayerServices;
import com.MI8.MI8.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finish")
public class FinishController {

    @Autowired
    GameServices gameServices;
    @Autowired
    PlayerServices playerServices;
    @Autowired
    RoomServices roomServices;

    @GetMapping("/finish-game")
    public String finishGame(ModelMap model) {
        model.addAttribute("message", "Congratulations, you have completed the game!");
        return "finish_game";
    }

}