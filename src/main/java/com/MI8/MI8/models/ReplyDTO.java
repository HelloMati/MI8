package com.MI8.MI8.models;

public class ReplyDTO {

    private String reply;
    private Item itemUsed;

    public ReplyDTO() {
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Item getItem() {
        return itemUsed;
    }

    public void setItem(Item item) {
        this.itemUsed = item;
    }
}
