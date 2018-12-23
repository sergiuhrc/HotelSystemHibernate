/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author SergiuH
 */
public class NewRoomDataInTable {

    SimpleIntegerProperty id_appartment = new SimpleIntegerProperty();
    SimpleStringProperty room_type = new SimpleStringProperty();
    SimpleIntegerProperty door_nr = new SimpleIntegerProperty();
    SimpleIntegerProperty beds = new SimpleIntegerProperty();
    SimpleIntegerProperty bath = new SimpleIntegerProperty();
    SimpleBooleanProperty separated_room = new SimpleBooleanProperty();
    SimpleIntegerProperty max_adults = new SimpleIntegerProperty();
    SimpleIntegerProperty max_childrens = new SimpleIntegerProperty();
    SimpleIntegerProperty price = new SimpleIntegerProperty();
    SimpleBooleanProperty available = new SimpleBooleanProperty();

    public int getId_appartment() {
        return id_appartment.get();
    }

    public void setId_appartment() {
        this.id_appartment = id_appartment;
    }

    public String getRoom_type() {
        return room_type.get();
    }

    public void setRoom_type() {
        this.room_type = room_type;
    }

    public int getDoor_nr() {
        return door_nr.get();
    }

    public void setDoor_nr() {
        this.door_nr = door_nr;
    }

    public int getBeds() {
        return beds.get();
    }

    public void setBeds() {
        this.beds = beds;
    }

    public int getBath() {
        return bath.get();
    }

    public void setBath() {
        this.bath = bath;
    }

    public boolean getSeparated_room() {
        return separated_room.get();
    }

    public void setSeparated_room() {
        this.separated_room = separated_room;
    }

    public int getMax_adults() {
        return max_adults.get();
    }

    public void setMax_adults() {
        this.max_adults = max_adults;
    }

    public int getMax_childrens() {
        return max_childrens.get();
    }

    public void setMax_childrens() {
        this.max_childrens = max_childrens;
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice() {
        this.price = price;
    }

    public boolean getAvailable() {
        return available.get();
    }

    public void setAvailable() {
        this.available = available;
    }
}
