/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author SergiuH
 */
public class FillComboBox {

     ConnectorDB cdb;
        ObservableList<Object> lista = FXCollections.observableArrayList();
    public FillComboBox(String dbTable,ComboBox cb) throws SQLException {
             try {
            this.cdb = new ConnectorDB();
           

        } catch (SQLException ex) {
            System.out.println("Nu e conectat");
        }
                ResultSet rs = null ;
                Statement statement=null;
        
        try {
            String query = "Select value  FROM "+dbTable;
            statement=cdb.connection.createStatement();
            
            rs = statement.executeQuery(query);
            while (rs.next()) {    
                String value = rs.getString("value");
               lista.add(value);
            }
            cb.getItems().addAll(lista);


            rs.close();
        } catch (SQLException e) {
            System.out.println("ComboBox Sql exceptie");
           if ( rs != null){
               rs.close();
               statement.close();
           }
        }finally {
            if ( rs != null ){
                rs.close();

            }else if (statement!= null){
                statement.close();


            }
        }
//
    }


    

}
