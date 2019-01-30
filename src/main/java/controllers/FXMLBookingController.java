
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.EntityBooking;
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
import javax.persistence.*;
import javax.transaction.Transactional;

public class FXMLBookingController implements Initializable {



    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    @FXML
    private TableView<EntityBooking> tabel = new TableView<EntityBooking>();
    @FXML
    private TableColumn<EntityBooking, Integer> id_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> door_nr_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> beds_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> baths_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Boolean> living_room_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> max_adults_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> max_childrens_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> bathrooms_tab = new TableColumn();
    @FXML
    private TableColumn<EntityBooking, Integer> price_tab = new TableColumn();
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
    @FXML
    private TableColumn<EntityBooking, Boolean> available_tab = new TableColumn();
    private ObservableList<EntityBooking> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewGetData();


        try {

            id_tab.setCellValueFactory(new PropertyValueFactory<>("id"));
            door_nr_tab.setCellValueFactory(new PropertyValueFactory<>("door"));
            beds_tab.setCellValueFactory(new PropertyValueFactory<>("beds"));
            baths_tab.setCellValueFactory(new PropertyValueFactory<>("baths"));
            living_room_tab.setCellValueFactory(new PropertyValueFactory<>("separatedRoom"));
            max_adults_tab.setCellValueFactory(new PropertyValueFactory<>("maxAdults"));
            max_childrens_tab.setCellValueFactory(new PropertyValueFactory<>("maxChildrens"));
            price_tab.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
            available_tab.setCellValueFactory(new PropertyValueFactory<>("available"));

            buildData();
            blockPastDays();
            blockDaysMinReservation();

            ObservableList<Boolean> lista = FXCollections.observableArrayList();
            lista.add(true);
            lista.add(false);
            separated_roomComboBox.getItems().addAll(lista);
            //separated_roomComboBox.setValue(false);
        } catch (Exception ex) {
            Logger.getLogger(FXMLBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        buildData();
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


            }

        });


    }

    @FXML
    public void calculatePriceButton(ActionEvent event) throws SQLException {

        int door = Integer.parseInt(room_number_Label.getText());
        EntityManager emPrice = emf.createEntityManager();
        TypedQuery<EntityBooking> query = emPrice.createQuery("Select pricePerNight From EntityBooking pricePerNight Where door_nr= '" + door + "'", EntityBooking.class);
        List<EntityBooking> result = query.getResultList();
        for (EntityBooking doc:result) {
            room_price_Label.setText(String.valueOf(doc.getPricePerNight() * DaysSetGet.getDays()));
        }

    }

    public void buildData()  {

            data = FXCollections.observableArrayList();
            EntityManager em = emf.createEntityManager();
            EntityBooking entityBooking = null;

            for (int i = 0; i < 100; i++) {
                entityBooking = em.find(EntityBooking.class, i);
                if (entityBooking != null&&entityBooking.isAvailable()) {
                    data.add(entityBooking);
                }
            }
            tabel.setItems(data);




    }

    public void tableViewGetData() {
        tabel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                room_number_Label.setText(String.valueOf(newValue.getDoor()));
            }catch (NullPointerException e){
                System.out.println("Null");
            }

        });
    }

    @FXML
    @Transactional
    void makeRezervation(ActionEvent event) {

        try {
            EntityBookingRezervation insertBooking = new EntityBookingRezervation();

            EntityManager emInsertBooking = emf.createEntityManager();
            EntityManager emUpdateAvailability = emf.createEntityManager();

            insertBooking.setName(nameTextField.getText());
            insertBooking.setSurrname(surrnameTextField.getText());
            insertBooking.setEmail(emailTextField.getText());
            Double identity = Double.parseDouble(identityTextField.getText().trim());
            insertBooking.setIdentity_no(identity);
            Double price = Double.parseDouble(room_price_Label.getText().trim());
            insertBooking.setStart_date(start_datePicker.getEditor().getText());
            insertBooking.setEnd_date(end_datePiker.getEditor().getText());
            int adults = Integer.parseInt(adultsTextField.getText().trim());
            int childrens = Integer.parseInt(childrensTextField.getText().trim());
            insertBooking.setAdults(adults);
            insertBooking.setChildrens(childrens);
            int door = Integer.parseInt(room_number_Label.getText());
            insertBooking.setDoor(door);
            insertBooking.setTotalPrice(price);

            emInsertBooking.getTransaction().begin();
            emInsertBooking.persist(insertBooking);
            emInsertBooking.getTransaction().commit();

            try {
                emUpdateAvailability.getTransaction().begin();
                emUpdateAvailability.createNativeQuery("UPDATE add_new_room SET available=false  where door_nr= '" + door + "'").executeUpdate();
                emUpdateAvailability.getTransaction().commit();
            }catch (Exception e){

                e.printStackTrace();
            }






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
