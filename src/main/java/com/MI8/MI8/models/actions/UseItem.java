package com.MI8.MI8.models.actions;

import com.MI8.MI8.models.Game;
import com.MI8.MI8.models.Item;
import com.MI8.MI8.models.Player;

public class UseItem extends ActionsProperties implements Actions {
    private Item item;

    //1st way

    public UseItem(Player player, Game game, Item item) {
        super(player, game);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    //2nd way
    public Use() {
        super("use", "Use an item in your inventory");
    }

    @Override
    public String execute(Player player, Game game) {
        Item item = player.getItem();
        if (item == null) {
            return "You have no item in your inventory.";
        } else {
            return "You have got " + item.getDescription() + ".";
        }
    }

}