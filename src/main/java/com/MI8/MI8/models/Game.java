package com.MI8.MI8.models;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "character")
    private PlayerCharacter character;

    @OneToOne
    @JoinColumn(name = "currentRoom")
    private Room currentRoom;

    public Game(int id, PlayerCharacter character, Room currentRoom) {
        this.id = id;
        this.character = character;
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

    public PlayerCharacter getCharacter() {
        return character;
    }

    public void setCharacter(PlayerCharacter character) {
        this.character = character;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}