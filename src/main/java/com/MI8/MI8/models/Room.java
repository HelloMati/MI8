package com.MI8.MI8.models;

import com.MI8.MI8.models.actions.Actions;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String RoomDescription;

    @OneToOne
    @JoinColumn(name = "roomContents")
    private Inventory roomContents;

//    @Column
//    private Set<Room> nextRooms;
//
//    @Column
//    private List<Actions> possibleActions;

    @OneToOne(mappedBy = "currentRoom")
    private Game game;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomDescription() {
        return RoomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        RoomDescription = roomDescription;
    }

    public Inventory getRoomContents() {
        return roomContents;
    }

    public void setRoomContents(Inventory roomContents) {
        this.roomContents = roomContents;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}