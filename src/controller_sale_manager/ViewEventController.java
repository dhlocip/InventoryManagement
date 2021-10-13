/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.VEvent;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewEventController implements Initializable {

    @FXML
    private DatePicker datefind;
    @FXML
    private Label find;
    @FXML
    private TableView<VEvent> viewEventTable;
    @FXML
    private TableColumn<VEvent, String> eventId;
    @FXML
    private TableColumn<VEvent, String> eventName;
    @FXML
    private TableColumn<VEvent, String> discount;
    @FXML
    private TableColumn<VEvent, String> startDate;
    @FXML
    private TableColumn<VEvent, String> endDate;
    @FXML
    private TableColumn<VEvent, String> productId;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {            
            getVEvent();
        } catch (SQLException ex) {
            Logger.getLogger(ViewEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    
    private void getVEvent() throws SQLException{
        
        ObservableList<VEvent> oList = new VEventModifier().getInfo();
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        
        viewEventTable.setItems(oList);
        
    }
    
}
