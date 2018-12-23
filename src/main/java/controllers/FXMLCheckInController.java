
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SergiuH
 */
public class FXMLCheckInController implements Initializable {

    ConnectorDB cdb;
    @FXML
    private TableView<NewCheckInDataInTable> tabel = new TableView<>();
    @FXML
    private TableColumn<NewCheckInDataInTable, Integer> doorTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, String> nameTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, String> surrnameTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, String> identityTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, String> checkInTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, String> checkOutTab = new TableColumn();
    @FXML
    private TableColumn<NewCheckInDataInTable, Double> priceTab = new TableColumn();

    private VBox vbox;

    @FXML
    private JFXTextField searchField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cdb = new ConnectorDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            doorTab.setCellValueFactory(new PropertyValueFactory<>("door"));
            nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
            surrnameTab.setCellValueFactory(new PropertyValueFactory<>("surrname"));
            identityTab.setCellValueFactory(new PropertyValueFactory<>("identityNo"));
            checkInTab.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
            checkOutTab.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
            priceTab.setCellValueFactory(new PropertyValueFactory<>("price"));

            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLCheckInController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        search();
searchRoom();
    }

    @FXML
    void showInfoAboutMealPlans(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/info/FXMLInfoMeal.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(new Scene(root1));
        stage.show();
        stage.setTitle("INFO");
    }
    private ObservableList<NewCheckInDataInTable> data2 = FXCollections.observableArrayList();
    ;
    private ObservableList<NewCheckInDataInTable> data = FXCollections.observableArrayList();

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        String SQL = "Select 	door_nr , name , surrname ,identity_number, start_date , end_date , total_price  FROM rezervation_room";

        // cdb.isDbConnected();
        Connection connection = cdb.connection;
        ResultSet rs = connection.createStatement().executeQuery(SQL);
        while (rs.next()) {
            NewCheckInDataInTable ti = new NewCheckInDataInTable();
            ti.door.set(rs.getInt("door_nr"));
            // ti.door_nr.set(rs.getInt("door_nr"));
            ti.name.set(rs.getString("name"));
            ti.surrname.set(rs.getString("surrname"));
            ti.identityNo.set(rs.getString("identity_number"));
            ti.checkIn.set(rs.getString("start_date"));
            ti.checkOut.set(rs.getString("end_date"));
            ti.price.set(rs.getDouble("total_price"));
            //  ti.available.set(rs.getBoolean("available"));

            data.addAll(ti);
        }
        tabel.setItems(data);
        rs.close();

    }

    @FXML
    void goToPaymentForm(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLPayment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(new Scene(root1));
        stage.show();
        stage.setTitle("Payment");
    }

    @FXML
    void searchCoustumer(ActionEvent event) {

    }

 public void searchRoom(){
    
     FilteredList<NewCheckInDataInTable> filteredData = new FilteredList<>(data, e -> true);
        searchField.setOnKeyPressed(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(tabinfo -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;

                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tabinfo.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        System.out.println("Lower"+lowerCaseFilter);
                        return true;
                    }
                    else    if (tabinfo.getSurrname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        System.out.println("Lower"+lowerCaseFilter);
                        return true;
                    }
                    else    if (tabinfo.getIdentityNo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        System.out.println("Lower"+lowerCaseFilter);
                        return true;
                    }

                    return false;
                });
            });
            SortedList<NewCheckInDataInTable> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tabel.comparatorProperty());
            tabel.setItems(sortedData);
        });
        
    }
}
