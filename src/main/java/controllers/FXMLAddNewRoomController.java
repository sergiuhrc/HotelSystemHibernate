/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author SergiuH
 */
public class FXMLAddNewRoomController implements Initializable {

    ConnectorDB cdb;
    ObservableList<Object> lista = FXCollections.observableArrayList();
    @FXML
    private TableView<NewRoomDataInTable> tabel = new TableView<>();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> id_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, String> room_type_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> door_nr_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> beds_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> baths_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Boolean> living_room_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> max_adults_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> max_childrens_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> bathrooms_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> price_tab = new TableColumn();
    @FXML
    private TableColumn<NewRoomDataInTable, Boolean> available_tab = new TableColumn();
    @FXML
    private JFXComboBox<?> bathComboBox;

    @FXML
    private JFXComboBox<?> bedsComboBox;

    @FXML
    private JFXTextField doorTextField;

    @FXML
    private JFXComboBox<?> maxAdults_ComboBox;

    @FXML
    private JFXTextField priceTextField;

    @FXML
    private JFXComboBox<?> maxchildrens_ComboBox;
    @FXML
    private JFXComboBox<?> room_type_ComboBox;

    @FXML
    private JFXCheckBox separated_rooms_CheckBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          final String ACTION_1 = "room_comboboxdb";
          final String ACTION_2 = "room_type_comboboxdb";
        try {
            new FillComboBox(ACTION_1, bedsComboBox);
            new FillComboBox(ACTION_1, bathComboBox);
            new FillComboBox(ACTION_1, maxAdults_ComboBox);
            new FillComboBox(ACTION_1, maxchildrens_ComboBox);
            new FillComboBox(ACTION_2, room_type_ComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }

         try {
            this.cdb = new ConnectorDB();

        } catch (SQLException ex) {
            System.out.println("Nu e conectat");
        }
    }

    @FXML
    void addNewRoom(ActionEvent event) throws SQLException {

  
        int door = Integer.valueOf(doorTextField.getText());
        int beds = Integer.valueOf(bedsComboBox.getSelectionModel().getSelectedItem().toString());
        int baths = Integer.valueOf(bathComboBox.getSelectionModel().getSelectedItem().toString());
        boolean separated = separated_rooms_CheckBox.isSelected();
        int maxAdults = Integer.valueOf(maxAdults_ComboBox.getSelectionModel().getSelectedItem().toString());
        int maxChildrens = Integer.valueOf(maxchildrens_ComboBox.getSelectionModel().getSelectedItem().toString());
        int price = Integer.valueOf(priceTextField.getText());
        String room = room_type_ComboBox.getSelectionModel().getSelectedItem().toString();
        String sql = "Insert into add_new_room SET door_nr=? , beds=? ,baths=?, separated_room=? , max_adults=? ,max_childrens=? ,price_per_night=? ,available=? ,type=?";
        //id_new_room , door_nr , beds ,baths, separated_room , max_adults , max_childrens , price_per_night ,available FROM add_new_room 
        Connection connection = cdb.connection;
        PreparedStatement pst = null;
        try {

            pst = connection.prepareStatement(sql);

            pst.setInt(1, door);
            pst.setInt(2, beds);
            pst.setInt(3, baths);
            pst.setBoolean(4, separated);
            pst.setInt(5, maxAdults);
            pst.setInt(6, maxChildrens);
            pst.setDouble(7, price);
            pst.setBoolean(8, true);
            pst.setString(9, room);
            pst.execute();
            pst.close();
            buildData();
            Notifications notificationBuilder = Notifications.create()
                    .title("Insert")
                    .text("Insertion is done")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
            //cleanFields();
            System.out.println("Succes");
        } catch (SQLException e) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Insert Error")
                    .text("Insert is imposible becouse of " + e)
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showInformation();
            System.out.println("Error" + e);


        }finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cancelAddNewRoom(ActionEvent event) {
        System.out.println("Cancel");
    }

    @FXML
    void showAllRooms(ActionEvent event) {

        id_tab.setCellValueFactory(new PropertyValueFactory<>("id_appartment"));
        room_type_tab.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        door_nr_tab.setCellValueFactory(new PropertyValueFactory<>("door_nr"));
        beds_tab.setCellValueFactory(new PropertyValueFactory<>("beds"));
        baths_tab.setCellValueFactory(new PropertyValueFactory<>("bath"));
        living_room_tab.setCellValueFactory(new PropertyValueFactory<>("separated_room"));
        max_adults_tab.setCellValueFactory(new PropertyValueFactory<>("max_adults"));
        max_childrens_tab.setCellValueFactory(new PropertyValueFactory<>("max_childrens"));
        price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
        available_tab.setCellValueFactory(new PropertyValueFactory<>("available"));
        try {

            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAddNewRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<NewRoomDataInTable> data = FXCollections.observableArrayList();

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String SQL = "Select id_new_room ,type, door_nr , beds ,baths, separated_room , max_adults , max_childrens , price_per_night ,available FROM add_new_room ";


        Connection connection = cdb.connection;
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                NewRoomDataInTable get_data = new NewRoomDataInTable();
                get_data.id_appartment.set(rs.getInt("id_new_room"));
                get_data.room_type.set(rs.getString("type"));
                get_data.door_nr.set(rs.getInt("door_nr"));
                get_data.beds.set(rs.getInt("beds"));
                get_data.bath.set(rs.getInt("baths"));
                get_data.separated_room.set(rs.getBoolean("separated_room"));
                get_data.max_adults.set(rs.getInt("max_adults"));
                get_data.max_childrens.set(rs.getInt("max_childrens"));
                get_data.price.set(rs.getInt("price_per_night"));
                get_data.available.set(rs.getBoolean("available"));

                data.addAll(get_data);
            }
            tabel.setItems(data);
        } catch (SQLException e) {

            e.printStackTrace();

        } finally {
            rs.close();

        }

    }
}
