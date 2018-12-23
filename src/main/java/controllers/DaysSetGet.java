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
public class DaysSetGet {
    private static int days;

    private DaysSetGet() {
    }

    public static int getDays() {
        return days;
    }

    public static void setDays(int days) {
        DaysSetGet.days = days;
    }
    
}
