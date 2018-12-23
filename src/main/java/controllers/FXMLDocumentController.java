/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author SergiuH
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView imageView;
    @FXML
    private HBox centerHBox;
    @FXML
    private StackPane stackPane;
    ConnectorDB cdb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//          try {
//            this.cdb = new ConnectorDB();
//            System.out.println("Conectat");
//
//        } catch (SQLException ex) {
//            System.out.println("Nu e conectat");
//        }
    }

    @FXML
    void addRooms(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLAddNewRoom.fxml"));
        Parent root =loader.load();
        stackPane.getChildren().setAll(root);

    }

    @FXML
    void backHome(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLHomePage.fxml"));
        Parent root =loader.load();
        stackPane.getChildren().setAll(root);
    }

    @FXML
    void bookingButtonAction(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLBooking2.fxml"));
        Parent root =loader.load();
        stackPane.getChildren().setAll(root);
    }
    @FXML
    void chartButtonAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLChartData.fxml"));
        Parent root =loader.load();
        stackPane.getChildren().setAll(root);
    }
    @FXML
    void checkInButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLCheck-In.fxml"));
        Parent root =loader.load();

        stackPane.getChildren().setAll(root);
    }

}
