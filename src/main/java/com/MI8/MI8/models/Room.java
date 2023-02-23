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
    @Column
    private String roomName;
    @Column(length = 512)
    private String firstEntranceMessage;
    @Column
    private String RoomDescription;
    @Column
    private Boolean haveEnteredRoom;
    @Column
    private String searchRoomMessage;

    @OneToOne(mappedBy = "currentRoom")
    @JsonIgnore
    private Game game;

    @Column
    private List<String> nextRooms;

    @Column
    private boolean lit;

    @ManyToMany(mappedBy = "roomCanBeUsedIn")
    @JsonIgnore
    private List<Item> itemsCanBeUsedHere;

    public Room(String name,String firstEntranceMessage,String roomDescription,boolean lit,String searchRoomMessage) {
        RoomDescription = roomDescription;
        this.roomName = name;
        this.searchRoomMessage = searchRoomMessage;
        this.firstEntranceMessage = firstEntranceMessage;
        this.nextRooms = new ArrayList<>();
        this.lit = lit;
        this.haveEnteredRoom = false;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFirstEntranceMessage() {
        return firstEntranceMessage;
    }

    public void setFirstEntranceMessage(String firstEntranceMessage) {
        this.firstEntranceMessage = firstEntranceMessage;
    }

    public String getRoomDescription() {
        return RoomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        RoomDescription = roomDescription;
    }

    public Boolean getHaveEnteredRoom() {
        return haveEnteredRoom;
    }

    public void setHaveEnteredRoom(Boolean haveEnteredRoom) {
        this.haveEnteredRoom = haveEnteredRoom;
    }

    public String getSearchRoomMessage() {
        return searchRoomMessage;
    }

    public void setSearchRoomMessage(String searchRoomMessage) {
        this.searchRoomMessage = searchRoomMessage;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<String> getNextRooms() {
        return nextRooms;
    }

    public void setNextRooms(List<String> nextRooms) {
        this.nextRooms = nextRooms;
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