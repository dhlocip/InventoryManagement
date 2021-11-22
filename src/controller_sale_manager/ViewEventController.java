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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewEventController implements Initializable {

    String eventID;
    
    
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
    @FXML
    private ComboBox<String> eventIdCombobox;
    @FXML
    private HBox search;
    @FXML
    private TableColumn<VEvent, String> userId;
    @FXML
    private TableColumn<VEvent, String> mfgDate;
    @FXML
    private TableColumn<VEvent, String> expDate;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   
        try {
            getShow();
            setValueEventIdComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(ViewEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    
    private void getShow() throws SQLException{
        
        ObservableList<VEvent> oList = new VEventModifier().getVEventInfo();
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata  
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        viewEventTable.setItems(oList);
        
    }

    private void setValueEventIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListEventId();
        

        eventID = eventIdCombobox.getValue();
        eventIdCombobox.setItems(oList);
        eventIdCombobox.setValue(oList.get(0));
        eventIdCombobox.setOnAction((t) -> {
            eventID = eventIdCombobox.getValue();
        });

    }
    
       public void getEventByEventId(String eventID) throws SQLException {

        ObservableList<VEvent> oList = new VEventModifier().getInfoByEventId(eventID);
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata  
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));   
        viewEventTable.setItems(oList);
    }

  

    @FXML
    private void getSearch(MouseEvent event) throws SQLException {
        getEventByEventId(eventIdCombobox.getValue());
    }
    
}
