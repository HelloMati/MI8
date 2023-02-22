package com.MI8.MI8.models;

import com.MI8.MI8.models.Player;
import com.MI8.MI8.models.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column
    protected String name;

    @Column
    protected String description;

    @Column
    protected String itemRarity;

    @ManyToMany(mappedBy = "inventory")
    @JsonIgnore
    protected List<Player> player;

    @ManyToMany
    @JoinTable(name = "items_rooms",
                joinColumns = @JoinColumn(name = "room_id"),
                inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonIgnore
    protected List<Room> roomCanBeUsedIn;


    public Item(String name,String description, String itemRarity) {
        this.name = name;
        this.description = description;
        this.itemRarity = itemRarity;
        this.roomCanBeUsedIn = new ArrayList<>();
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        this.name = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemRarity() {
        return itemRarity;
    }

    public void setItemRarity(String itemRarity) {
        this.itemRarity = itemRarity;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public List<Room> getRoomCanBeUsedIn() {
        return roomCanBeUsedIn;
    }

    public void setRoomCanBeUsedIn(List<Room> roomCanBeUsedIn) {
        this.roomCanBeUsedIn = roomCanBeUsedIn;
    }
}