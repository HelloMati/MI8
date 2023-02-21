package com.MI8.MI8.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.NVarcharJdbcType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 512)
    private String firstEntranceMessage;
    @Column
    private String RoomDescription;
    @Column
    private Boolean haveEnteredRoom;

    @OneToOne(mappedBy = "currentRoom")
    @JsonIgnore
    private Game game;

    @Column
    private List<Integer> nextRoomIds;

    @Column
    private boolean lit;

    @ManyToMany(mappedBy = "roomCanBeUsedIn")
    @JsonIgnore
    private List<Item> itemsCanBeUsedHere;

    public Room(String firstEntranceMessage,String roomDescription,boolean lit) {
        RoomDescription = roomDescription;
        this.firstEntranceMessage = firstEntranceMessage;
        this.nextRoomIds = new ArrayList<>();
        this.lit = lit;
        this.haveEnteredRoom = false;
    }

    public Room() {
    }

    public List<Integer> getNextRooms() {
        return nextRoomIds;
    }

    public void setNextRooms(List<Integer> nextRooms) {
        this.nextRoomIds = nextRooms;
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


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getFirstEntranceMessage() {
        return firstEntranceMessage;
    }

    public void setFirstEntranceMessage(String firstEntranceMessage) {
        this.firstEntranceMessage = firstEntranceMessage;
    }

    public Boolean getHaveEnteredRoom() {
        return haveEnteredRoom;
    }

    public void setHaveEnteredRoom(Boolean haveEnteredRoom) {
        this.haveEnteredRoom = haveEnteredRoom;
    }

    public List<Integer> getNextRoomIds() {
        return nextRoomIds;
    }

    public void setNextRoomIds(List<Integer> nextRoomIds) {
        this.nextRoomIds = nextRoomIds;
    }

    public boolean isLit() {
        return lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    public List<Item> getItemsCanBeUsedHere() {
        return itemsCanBeUsedHere;
    }

    public void setItemsCanBeUsedHere(List<Item> itemsCanBeUsedHere) {
        this.itemsCanBeUsedHere = itemsCanBeUsedHere;
    }
}