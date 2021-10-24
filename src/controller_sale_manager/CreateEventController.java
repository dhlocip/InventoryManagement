/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import controller_app.UIDashboardSaleManagerController;
import data.EventDetail;
import data.Events;
import data.User;
import data_modifier.UserModifier;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    String lUserId;
    String userIdTF;
    
    String eventDetailID, productID, Name, disc, startd, endd, mfgd, expd;

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
    @FXML
    private TableColumn<EventDetail, String> mfgDate;
    @FXML
    private TableColumn<EventDetail, String> expDate;
    @FXML
    private DatePicker txtmfgDate;
    @FXML
    private DatePicker txtexpDate;

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

    private void getShowEventDetail() throws SQLException {
        ObservableList<EventDetail> oList = new VEventModifier().getEventDetailInfo();
        eventIdEventDetail.setCellValueFactory(new PropertyValueFactory<>("eventIdDetail"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
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
            evtID = eventIDCombobox.getValue();
        });

    }

    @FXML
    private void startDateAction(ActionEvent event) {
        LocalDate start = txtStartDate.getValue();
        startd = String.valueOf(start);
    }

    @FXML
    private void endDateAction(ActionEvent event) {
        LocalDate end = txtEndDate.getValue();
        endd = String.valueOf(end);
    }

    @FXML
    private void eventIdAction(ActionEvent event) {
        eventDetailID = eventIDCombobox.getValue();
    }

    @FXML
    private void productIdAction(ActionEvent event) {
        productID = productIdcombobox.getValue();
    }

    @FXML
    private void mfgDateAction(ActionEvent event) {
        LocalDate mfg = txtmfgDate.getValue();
        mfgd = String.valueOf(mfg);
    }

    @FXML
    private void expDateAction(ActionEvent event) {
        LocalDate exp = txtexpDate.getValue();
        expd = String.valueOf(exp);
    }

    @FXML
    private void getMouseClickEvent(MouseEvent event) {
    }

    
    
    @FXML
    private void createEventClick(MouseEvent event) throws SQLException {
        if (txtEventName.getText().isEmpty()
                || (txtEndDate.getValue() == null) || (txtStartDate.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
            
            

        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
            User user = new User();
            user = new UserModifier().getUser(lUserId);
            userIdTF = user.getPersonId();
            Name = txtEventName.getText();
            
            Events events = new Events();
            events.setUserId(userIdTF);
            events.setEventName(Name);
            events.setStartDate(startd);
            events.setEndDate(endd);
            
            if (new VEventModifier().getCreateEvents(events)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();
                
            }
            
        }
        
        getShowEvent();
        setValueEventIdComboBox();

    }

    @FXML
    private void createEventDetailClick(MouseEvent event) throws SQLException {
        if (txtDiscount.getText().isEmpty() || (txtmfgDate.getValue() == null) || (txtexpDate.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();

        } else {
            disc =txtDiscount.getText();
            EventDetail eventDetail = new EventDetail();
            eventDetail.setEventIdDetail(evtID);
            eventDetail.setProductId(proID);
            eventDetail.setDiscount(disc);
            eventDetail.setMfgDate(mfgd);
            eventDetail.setExpDate(expd);
            
            if (new VEventModifier().getCreateEventDetail(eventDetail)) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();
                
                
            }
        }
        getShowEventDetail();
        
    }

    @FXML
    private void getMouseClickEventDetail(MouseEvent event) {
    }

}
