/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.EventDetail;
import data.Events;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class CreateEventController implements Initializable {

    String proID;
    String evtID;
    String eventID,eventDetailID,productID,Name,disc,startd,endd; 
    
    @FXML
    private ComboBox<String> productIdcombobox;
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
    private TableColumn<Events, String> eventName;
    @FXML
    private TableColumn<EventDetail, String> productId;
    @FXML
    private TableColumn<EventDetail, String> discount;
    @FXML
    private TableColumn<Events, String> startDate;
    @FXML
    private TableColumn<Events, String> endDate;
    @FXML
    private HBox createEventDetail;
    @FXML
    private Label createlabel1;
    @FXML
    private HBox createEvent;
    @FXML
    private TableView<Events> createEventTable;
    @FXML
    private TableView<EventDetail> createEventDetailTable;
    @FXML
    private TableColumn<Events, String> eventIdEvent;
    @FXML
    private TableColumn<EventDetail, String> eventIdEventDetail;
    @FXML
    private ComboBox<String> eventIDCombobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getShowEvent();
            getShowEventDetail();
            setValueProductIdComboBox();
            setValueEventIdComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(CreateEventController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }


    private void getShowEvent() throws SQLException {
        ObservableList<Events> oList = new VEventModifier().getVEventsInfo();
        eventIdEvent.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        createEventTable.setItems(oList);
    }
    
    private void getShowEventDetail() throws SQLException{
        ObservableList<EventDetail> oList = new VEventModifier().getEventDetailInfo();
        eventIdEventDetail.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        createEventDetailTable.setItems(oList);
                
        }

    private void setValueProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListProductId();
        productIdcombobox.setItems(oList);
        productIdcombobox.setValue(oList.get(0));

        proID = productIdcombobox.getValue();

        productIdcombobox.setOnAction((t) -> {
            proID = productIdcombobox.getValue();
        });

    }
    
     private void setValueEventIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListEventId();
        eventIDCombobox.setItems(oList);
        eventIDCombobox.setValue(oList.get(0));

        evtID = eventIDCombobox.getValue();

        eventIDCombobox.setOnAction((t) -> {
            evtID = productIdcombobox.getValue();
        });

    }
    
   

    

    @FXML
    private void createEventDetailClick(MouseEvent event) {
    }


    @FXML
    private void getMouseClickEvent(MouseEvent event) throws SQLException {
//        ObservableList<Events> oList = new VEventModifier().getVEventsInfo();
//        createEventTable.setItems(oList);
//        
//        
//        if (txtEventName.getText().isEmpty() ||  
//                (txtEndDate.getValue()== null) || (txtStartDate.getValue() == null)){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please Fill All Data");
//            alert.showAndWait();
//            
//        } else{
//            
//            Events events = new Events();
//            events.setEventName(txtEventName.getText());
//            events.setStartDate(String.valueOf(txtStartDate.getValue()));
//            events.setStartDate(String.valueOf(txtEndDate.getValue()));
//        
//        }
    
    }

    private void getMouseClickEventEvent(MouseEvent event) throws SQLException {
//        ObservableList<Events> oList = new VEventModifier().getVEventsInfo();
//        createEventTable.setItems(oList);
//        
//        
//        
//        if ((eventIDCombobox.getValue()== null)|| txtDiscount.getText().isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please Fill All Data");
//            alert.showAndWait();
//        } else{
//            EventDetail eventDetail = new EventDetail();
//            eventDetail.setProductId(productIdcombobox.getValue());
//            eventDetail.setDiscount(txtDiscount.getText());
//           
//        }
    }

    @FXML
    private void createEventClick(MouseEvent event) throws SQLException {
        
        ObservableList<Events> oList = new VEventModifier().getVEventsInfo();
        createEventTable.setItems(oList);
        
        
        if (txtEventName.getText().isEmpty() ||  
                (txtEndDate.getValue()== null) || (txtStartDate.getValue() == null)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
            
        } else{
            
            Events events = new Events();
            events.setEventName(txtEventName.getText());
            events.setStartDate(String.valueOf(txtStartDate.getValue()));
            events.setStartDate(String.valueOf(txtEndDate.getValue()));
        
        }
    
        
    }

    @FXML
    private void createEvenDetailClick(MouseEvent event) throws SQLException {
        ObservableList<EventDetail> oList = new VEventModifier().getEventDetailInfo();
        createEventDetailTable.setItems(oList);
        
        
        
        if ((eventIDCombobox.getValue()== null)|| txtDiscount.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        } else{
            EventDetail eventDetail = new EventDetail();
            eventDetail.setProductId(productIdcombobox.getValue());
            eventDetail.setDiscount(txtDiscount.getText());
           
        }
        
        
        
        
    }

    @FXML
    private void getMouseClickEventDetail(MouseEvent event) {
    }

   

}
