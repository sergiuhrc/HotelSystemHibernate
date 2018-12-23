
package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author SergiuH
 */
public class NewCheckInDataInTable {
//    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleIntegerProperty door = new SimpleIntegerProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty surrname = new SimpleStringProperty();
    SimpleStringProperty identityNo = new SimpleStringProperty();
    SimpleStringProperty checkIn = new SimpleStringProperty();
    SimpleStringProperty checkOut = new SimpleStringProperty();
    SimpleDoubleProperty price = new SimpleDoubleProperty();

 

    public int getDoor() {
        return door.get();
    }

    public void setDoor() {
        this.door = door;
    }

    public String getName() {
        return name.get();
    }

    public void setName( ) {
        this.name = name;
    }

    public String getSurrname() {
        return surrname.get();
    }

    public void setSurrname( ) {
        this.surrname = surrname;
    }

    public String getIdentityNo() {
        return identityNo.get();
    }

    public void setIdentityNo( ) {
        this.identityNo = identityNo;
    }

    public String getCheckIn() {
        return checkIn.get();
    }

    public void setCheckIn( ) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut.get();
    }

    public void setCheckOut( ) {
        this.checkOut = checkOut;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice( ) {
        this.price = price;
    }
    
}
