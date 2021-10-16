/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;


import com.sun.javafx.logging.PlatformLogger.Level;
import data.VEvent;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class CreateEventController implements Initializable {

    @FXML
    private ComboBox<VEvent> productIdcombobox;
    @FXML
    private Label createlabel;
    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtDiscount;
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private TableView<VEvent> createEvent;
    @FXML
    private TableColumn<VEvent, String> eventId;
    @FXML
    private TableColumn<VEvent, String> eventName;
    @FXML
    private TableColumn<VEvent, String> productId;
    @FXML
    private TableColumn<VEvent, String> discount;
    @FXML
    private TableColumn<VEvent, String> userId;
    @FXML
    private TableColumn<VEvent, String> startDate;
    @FXML
    private TableColumn<VEvent, String> endDate;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(CreateEventController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }    
    
    private void getShow() throws SQLException{
        
        ObservableList<VEvent> oList = new VEventModifier().getEventInfo();
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        
        createEvent.setItems(oList);
        
    }
        
    
}
