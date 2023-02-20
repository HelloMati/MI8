package com.MI8.MI8.models.actions;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Player;

public abstract class ActionsProperties {

    private Player player;
    private Game game;

    public ActionsProperties(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}