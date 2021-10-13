/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;


import data.EventDetail;
import data.Events;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<?> productIdcombobox;
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
    private TableView<Events> createEvent;
    @FXML
    private TableColumn<Events, String> eventId;
    @FXML
    private TableColumn<Events, String> eventName;
    @FXML
    private TableColumn<EventDetail, String> productId;
    @FXML
    private TableColumn<EventDetail, String> discount;
    @FXML
    private TableColumn<Events, String> userId;
    @FXML
    private TableColumn<EventDetail, String> startDate;
    @FXML
    private TableColumn<EventDetail, String> endDate;

    private ObservableList<Events> eventsList;
    private ObservableList<EventDetail> eventDetailList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        eventsList = FXCollections.observableArrayList(
        //new Events(eventId, userId, eventName, startDate, endDate)
        new Events("1", "ID001", "Sale 50%","11/11/2020", "12/12/2020"),
        new Events("2", "ID002", "Sale 80%","12/12/2020", "21/12/2020")
        );
        
        eventDetailList = FXCollections.observableArrayList(
        //new EventDetail(eventId, productId, discount, mfdDate, expDate)
        new EventDetail("1", "PR001", "50%", "11/11/2020", "12/12/2020"),
        new EventDetail("2", "PR002", "80%", "12/12/2020", "21/12/2020")
        );
//        newRequestId.setCellValueFactory(new PropertyValueFactory<NewRequest, String>("requestId"));
        eventId.setCellValueFactory(new PropertyValueFactory<Events, String>("eventId"));
        userId.setCellValueFactory(new PropertyValueFactory<Events, String>("userId"));
        
        
        createEvent.setItems(eventsList);
        //createEvent.setItems(eventDetailList);
    }    
    
    

    
    
}
