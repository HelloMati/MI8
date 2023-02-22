package com.MI8.MI8.models;

import java.util.List;

public class ReplyDTO {

    private String reply;
    private List<String> roomsYouCanEnter;
    private List<String> inventory;

    public ReplyDTO(String reply, List<String> roomsYouCanEnter, List<String> inventory) {
        this.reply = reply;
        this.roomsYouCanEnter = roomsYouCanEnter;
        this.inventory = inventory;
    }

    public ReplyDTO(String reply) {
        this.reply = reply;
    }

    public ReplyDTO() {
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<String> getRoomsYouCanEnter() {
        return roomsYouCanEnter;
    }

    public void setRoomsYouCanEnter(List<String> roomsYouCanEnter) {
        this.roomsYouCanEnter = roomsYouCanEnter;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
}
