package com.MI8.MI8.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "player")
    @JsonIgnoreProperties({"players"})
    private Player player;

    @OneToOne
    @JoinColumn(name = "currentRoom")
    @JsonIgnoreProperties({"rooms"})
    private Room currentRoom;

    @Column
    private boolean playerHasWon;

    public Game(int id, Player character, Room currentRoom) {
        this.id = id;
        this.player = character;
        this.currentRoom = currentRoom;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getCharacter() {
        return player;
    }

    public void setCharacter(Player character) {
        this.player = character;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isPlayerHasWon() {
        return playerHasWon;
    }

    public void setPlayerHasWon(boolean playerHasWon) {
        this.playerHasWon = playerHasWon;
    }
}