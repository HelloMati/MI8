package com.MI8.MI8.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int maxCapacity;

//    @ManyToMany
//    @JoinTable(name = "inventory_items",
//                joinColumns = @JoinColumn(name="inventory_id"),
//                inverseJoinColumns = @JoinColumn(name = "item_id"))
//    @JsonIgnoreProperties("inventory")
//    private Item item;

    @OneToOne(mappedBy = "inventory")
    private PlayerCharacter player;

    @OneToOne(mappedBy = "roomContents")
    private Room room;


    public Inventory(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Inventory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}