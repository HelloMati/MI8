package com.MI8.MI8.models;

import jakarta.persistence.*;

@Entity
@Table(name = "characters")
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String string;

    @OneToOne(mappedBy = "character")
    private Game game;

    @OneToOne
    @JoinColumn(name = "inventory")
    private Inventory inventory;

    public PlayerCharacter(String name) {
        this.string = name;
    }

    public PlayerCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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