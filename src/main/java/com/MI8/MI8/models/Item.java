//package com.MI8.MI8.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "items")
//public class Item {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//
//    @Column
//    private String description;
//
//    @Column
//    private String itemRarity;
//
//    @ManyToMany(mappedBy = "item")
//    @JsonIgnoreProperties("item")
//    private Inventory inventory;
//
//    public Item(int id, String description, String itemRarity, Inventory inventory) {
//        this.id = id;
//        this.description = description;
//        this.itemRarity = itemRarity;
//        this.inventory = inventory;
//    }
//
//    public Item(int id, String description, String itemRarity) {
//        this.id = id;
//        this.description = description;
//        this.itemRarity = itemRarity;
//    }
//
//    public Item() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getItemRarity() {
//        return itemRarity;
//    }
//
//    public void setItemRarity(String itemRarity) {
//        this.itemRarity = itemRarity;
//    }
//
//    public Inventory getInventory() {
//        return inventory;
//    }
//
//    public void setInventory(Inventory inventory) {
//        this.inventory = inventory;
//    }
//}