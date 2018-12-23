/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author SergiuH
 */

public class GetDataForBooking {

    private String startDate;
    private String endDate;
    private String name;
    private String surrname;
    private String email;
    private double identityNo;
    private String city;
    private String address;
    private int adults;
    private int childrens;
    private boolean separatedRoom;
    private int door;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getStart_date() {

        return startDate;

    }

    public void setStart_date(String start_date) {
        this.startDate = start_date;
    }

    public String getEnd_date() {

        return endDate;

    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isSeparated_room() {
        return separatedRoom;
    }

    public void setSeparated_room(boolean separated_room) {
        this.separatedRoom = separated_room;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

}
