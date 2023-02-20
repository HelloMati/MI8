package com.MI8.MI8.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToOne(mappedBy = "player")
    @JsonIgnoreProperties(value = "player")
    private Game game;

    @ManyToMany
    @JoinTable(name = "player_item",
                joinColumns = @JoinColumn(name = "player_id"),
                inverseJoinColumns = @JoinColumn(name = "item_id"))
    @JsonIgnoreProperties("player")
    private List<Item> inventory;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
    }

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

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}