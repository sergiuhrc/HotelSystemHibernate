
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class FXMLBookingController implements Initializable {

    ConnectorDB cdb;
    GetDataForBooking gdfb = new GetDataForBooking();
    @FXML
    private TableView<NewRoomDataInTable> tabel = new TableView<>();
    @FXML
    private TableColumn<NewRoomDataInTable, Integer> id_tab = new TableColumn();
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
    private DatePicker start_datePicker;
    @FXML
    private DatePicker end_datePiker;
    @FXML
    private JFXTextField identityTextField;

    @FXML
    private JFXTextField childrensTextField;

    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXTextField adressTextField;
    @FXML
    private JFXComboBox<Boolean> separated_roomComboBox;

    @FXML
    private JFXTextField cityTextField;
    @FXML
    private JFXTextField adultsTextField;

    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField surrnameTextField;
    @FXML
    private Label room_number_Label;
    @FXML
    private Label room_price_Label;
    @FXML
    private JFXButton priceButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewGetData();
        try {
            this.cdb = new ConnectorDB();
//                     tabel.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) -> {
//                try {
//                    room_price_Label.setText(String.valueOf(newValue1.getPrice()*DaysSetGet.getDays()));
//                  
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            id_tab.setCellValueFactory(new PropertyValueFactory<>("id_appartment"));
            door_nr_tab.setCellValueFactory(new PropertyValueFactory<>("door_nr"));
            beds_tab.setCellValueFactory(new PropertyValueFactory<>("beds"));
            baths_tab.setCellValueFactory(new PropertyValueFactory<>("bath"));
            living_room_tab.setCellValueFactory(new PropertyValueFactory<>("separated_room"));
            max_adults_tab.setCellValueFactory(new PropertyValueFactory<>("max_adults"));
            max_childrens_tab.setCellValueFactory(new PropertyValueFactory<>("max_childrens"));
            price_tab.setCellValueFactory(new PropertyValueFactory<>("price"));
            available_tab.setCellValueFactory(new PropertyValueFactory<>("available"));

            buildData();
            blockPastDays();
            blockDaysMinReservation();

            ObservableList<Boolean> lista = FXCollections.observableArrayList();
            lista.add(true);
            lista.add(false);
            separated_roomComboBox.getItems().addAll(lista);
            //separated_roomComboBox.setValue(false);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        nameTextField.setText("");
        surrnameTextField.setText("");
        emailTextField.setText("");
        identityTextField.setText("");
        adultsTextField.setText("");
        childrensTextField.setText("");
        room_number_Label.setText("");
        cityTextField.setText("");
        adressTextField.setText("");
        start_datePicker.getEditor().setText("");
        end_datePiker.getEditor().setText("");

    }

    private void blockPastDays() {
        LocalDate today = LocalDate.now();
        start_datePicker.setDayCellFactory((param) -> {
            return new DateCell() {

                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });
    }

    private void blockDaysMinReservation() {
        start_datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                System.out.println("aici");
                end_datePiker.setDayCellFactory((param) -> {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate date, boolean empty) {
                            super.updateItem(date, empty);
                            LocalDate today2 = start_datePicker.getValue();
                            setDisable(empty || date.compareTo(today2) < 1);

                        }

                    };

                });

            }

            end_datePiker.setDisable(false);
        });

        end_datePiker.focusedProperty().addListener(new ChangeListener<Boolean>() {
            private int days = 0;

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    long data1 = start_datePicker.getValue().toEpochDay();
                    long data2 = end_datePiker.getValue().toEpochDay();
                    days = (int) Math.abs(data1 - data2);
                }
                DaysSetGet.setDays(days);
                DaysSetGet.getDays();
                System.out.println("Days " + days);

//                   tabel.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) -> {
//                try {
//                    room_price_Label.setText(String.valueOf(newValue1.getPrice()*DaysSetGet.getDays()));
//                  
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
            }

        });

        // room_price_Label.setText(String.valueOf(newValue.getPrice()*DaysSetGet.getDays()));
    }

    @FXML
    public void calculatePriceButton(ActionEvent event) throws SQLException {
//        double price =0
        Connection connection = cdb.connection;

        ResultSet rs = null;
        try {
            int door = Integer.parseInt(room_number_Label.getText());
            String SQL = "Select price_per_night From add_new_room Where door_nr= '" + door + "'";
            rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                rs.getInt("price_per_night");
//            System.out.println( "Element "+  rs.getInt("price_per_night"));
                room_price_Label.setText(String.valueOf(rs.getInt("price_per_night") * DaysSetGet.getDays()));
            }

//        System.out.println(" Calculate price = ");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLBookingController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();

        }


    }

    private ObservableList<NewRoomDataInTable> data = FXCollections.observableArrayList();

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String SQL = "Select id_new_room , door_nr , beds ,baths, separated_room , max_adults , max_childrens , price_per_night ,available FROM add_new_room Where available = true";

        // cdb.isDbConnected();
        Connection connection = cdb.connection;
        ResultSet rs = null;
        try{
                    rs=connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                NewRoomDataInTable ti = new NewRoomDataInTable();
                ti.id_appartment.set(rs.getInt("id_new_room"));
                ti.door_nr.set(rs.getInt("door_nr"));
                ti.beds.set(rs.getInt("beds"));
                ti.bath.set(rs.getInt("baths"));
                ti.separated_room.set(rs.getBoolean("separated_room"));
                ti.max_adults.set(rs.getInt("max_adults"));
                ti.max_childrens.set(rs.getInt("max_childrens"));
                ti.price.set(rs.getInt("price_per_night"));
                ti.available.set(rs.getBoolean("available"));

                data.addAll(ti);
            }
        tabel.setItems(data);
        rs.close();

    }catch (SQLException e){
        e.printStackTrace();
    }finally {

        rs.close();
     }


    }

    public void tableViewGetData() {
        tabel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            room_number_Label.setText(String.valueOf(newValue.getDoor_nr()));

        });

    }

    @FXML
    void makeRezervation(ActionEvent event) throws SQLException {

        try {

            gdfb.setName(nameTextField.getText());
            gdfb.setSurrname(surrnameTextField.getText());
            gdfb.setEmail(emailTextField.getText());
            Double identity = Double.parseDouble(identityTextField.getText().trim());
            gdfb.setIdentity_no(identity);
            Double price = Double.parseDouble(room_price_Label.getText().trim());
           // gdfb.setIdentity_no(price);
            // gdfb.setCity(cityTextField.getText().trim());
            // gdfb.setAddress(adressTextField.getText().trim());
            gdfb.setStart_date(start_datePicker.getEditor().getText());
            gdfb.setEnd_date(end_datePiker.getEditor().getText());
            int adults = Integer.parseInt(adultsTextField.getText().trim());
            int childrens = Integer.parseInt(childrensTextField.getText().trim());
            gdfb.setAdults(adults);
            gdfb.setChildrens(childrens);
            int door = Integer.parseInt(room_number_Label.getText());
            gdfb.setDoor(door);
            gdfb.setTotalPrice(price);

            // cdb.isDbConnected();
            String sql = "insert into rezervation_room"
                    + "(name,surrname , email,identity_number , adults,childrens,start_date,end_date,door_nr,total_price)"
                    + "values(?,?,?,?,?,?,?,?,?,?) ";
            String sqlUpdate = "UPDATE add_new_room SET available=false  where door_nr= '" + door + "'";
            PreparedStatement pst = cdb.connection.prepareStatement(sql);
            PreparedStatement pstUpdate = cdb.connection.prepareStatement(sqlUpdate);
            pst.setString(1, gdfb.getName());
            pst.setString(2, gdfb.getSurrname());
            pst.setString(3, gdfb.getEmail());
            pst.setDouble(4, gdfb.getIdentity_no());
            pst.setInt(5, gdfb.getAdults());
            pst.setInt(6, gdfb.getChildrens());
            pst.setString(7, gdfb.getStart_date());
            pst.setString(8, gdfb.getEnd_date());
            
            pst.setInt(9, gdfb.getDoor());
            pst.setDouble(10, gdfb.getTotalPrice());
            pst.executeUpdate();
            pst.close();
            pstUpdate.executeUpdate();
            pstUpdate.close();

            refresh();
            Notifications notificationBuilder = Notifications.create()
                    .title("Rezervare finalizata")
                    .text("Rezervare finalizata cu succes")
                    .graphic(null)
                    .hideAfter(Duration.seconds(7))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showConfirm();

        } catch (Exception e) {
            Notifications notificationBuilder = Notifications.create()
                    .title("Error")
                    .text("Rezervarea nu a fost finalizata")
                    .graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showError();
        }
    }

}
