/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author SergiuH
 */
public class FXMLChartDataController implements Initializable {
    @FXML
    private Circle c3;

    @FXML
    private Circle c1;

    @FXML
    private Circle c2;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setRotate(c3, true, 360, 10);
        setRotate(c2, true, 180, 18);
        setRotate(c1, true, 145, 24);
    }    
    private void setRotate(Circle c,boolean reverse, int angle, int duration){
        
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration),c);
        rotateTransition.setAutoReverse(reverse);
        rotateTransition.setByAngle(angle);
        rotateTransition.setDelay(Duration.seconds(0));
        rotateTransition.setRate(3);
        rotateTransition.setCycleCount(Integer.MAX_VALUE);
        rotateTransition.play();
    
    
    
    }
    

}
