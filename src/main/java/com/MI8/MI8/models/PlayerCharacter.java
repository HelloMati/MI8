package com.MI8.MI8.models;

import jakarta.persistence.*;

import javax.naming.Name;

@Entity
@Table(name = "characters")
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToOne(mappedBy = "character")
    private Game game;

    @OneToOne
    @JoinColumn(name = "inventory")
    private Inventory inventory;

    public PlayerCharacter(String name) {
        this.name = name;
    }

    public PlayerCharacter(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}