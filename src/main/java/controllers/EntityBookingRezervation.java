/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.persistence.*;

/**
 *
 * @author SergiuH
 */
@Entity
@Table(name = "rezervation_room")
public class EntityBookingRezervation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    @Column(name = "name")
    private String name;
    @Column(name = "surrname")
    private String surrname;
    @Column(name = "email")
    private String email;
    @Column(name = "identity_number")
    private double identityNo;

    @Column(name = "adults")
    private int adults;
    @Column(name = "childrens")
    private int childrens;

    @Column(name = "door_nr")
    private int door;
    @Column(name = "total_price")
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getStart_date() { return startDate; }

    public void setStart_date(String start_date) {
        this.startDate = start_date;
    }

    public String getEnd_date() { return endDate; }

    public void setEnd_date(String end_date) {
        this.endDate = end_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getIdentity_no() {
        return identityNo;
    }

    public void setIdentity_no(double identity_no) {
        this.identityNo = identity_no;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildrens() {
        return childrens;
    }

    public void setChildrens(int childrens) {
        this.childrens = childrens;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

}
