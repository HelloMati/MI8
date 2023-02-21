package com.MI8.MI8.models.actions;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;

public class TakeItem extends ActionsProperties implements Actions {
    private Item item;

    public TakeItem(Player player, Game game, Item item) {
        super(player, game);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}