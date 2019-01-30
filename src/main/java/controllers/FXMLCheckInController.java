
package controllers;

import com.jfoenix.controls.JFXTextField;
import entity.EntityCheckIn;
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
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author SergiuH
 */
public class FXMLCheckInController implements Initializable {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    @FXML
    private TableView<EntityCheckIn> tabel = new TableView<>();
    @FXML
    private TableColumn<EntityCheckIn, Integer> doorTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, String> nameTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, String> surrnameTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, String> identityTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, String> checkInTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, String> checkOutTab = new TableColumn();
    @FXML
    private TableColumn<EntityCheckIn, Double> priceTab = new TableColumn();
    @FXML
    private JFXTextField searchFieldName;
    @FXML
    private JFXTextField searchFieldSurrname;
    private ObservableList<EntityCheckIn> data = FXCollections.observableArrayList();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

            doorTab.setCellValueFactory(new PropertyValueFactory<>("door"));
            nameTab.setCellValueFactory(new PropertyValueFactory<>("name"));
            surrnameTab.setCellValueFactory(new PropertyValueFactory<>("surrname"));
            identityTab.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
            checkInTab.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            checkOutTab.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            priceTab.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

            buildData();
//            searchRoom();
    }

    public void buildData() {
        data = FXCollections.observableArrayList();
        EntityManager em = emf.createEntityManager();
        EntityCheckIn entityCheckIn= null;

        for (int i = 0; i <100 ; i++) {
                entityCheckIn = em.find(EntityCheckIn.class,i);
                if (entityCheckIn!=null){
                    data.add(entityCheckIn);
                }
        }
         tabel.setItems(data);
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
         ObservableList<EntityCheckIn> data2 = FXCollections.observableArrayList();
        System.out.println("Salutr!!!!!!!!!!!");
        ArrayList<EntityCheckIn> list = new ArrayList<>();
        list.addAll(tabel.getItems().stream().distinct().filter(ele -> ele.getName().equals(searchFieldName.getText()) && ele.getSurrname().equals(searchFieldSurrname.getText())).collect(Collectors.toList()));
        EntityCheckIn setFiltered = new EntityCheckIn();
        if (list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                setFiltered.setDoor(Integer.parseInt(String.valueOf(list.get(i).getDoor())));
                setFiltered.setName(list.get(i).getName());
                setFiltered.setSurrname(list.get(i).getSurrname());
                setFiltered.setIdentityNumber(Long.parseLong(String.valueOf(list.get(i).getIdentityNumber())));
                setFiltered.setStartDate(list.get(i).getStartDate());
                setFiltered.setEndDate(list.get(i).getEndDate());
                setFiltered.setTotalPrice(Double.parseDouble(String.valueOf(list.get(i).getTotalPrice())));
                data2.addAll(setFiltered);
            }

            tabel.setItems(data2);
            System.out.println(list.size());
        }else {


            JOptionPane.showMessageDialog(null,"We can not find this person","Error",JOptionPane.ERROR_MESSAGE);
            buildData();
        }


    }

 public void searchRoom(){
    
     FilteredList<EntityCheckIn> filteredData = new FilteredList<>(data, e -> true);
        searchFieldName.setOnKeyPressed(e -> {
            searchFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(tabinfo -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;

                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (tabinfo.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ||tabinfo.getSurrname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }



                    return false;
                });
            });
            SortedList<EntityCheckIn> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tabel.comparatorProperty());
            tabel.setItems(sortedData);
        });
        
    }
}
