/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import controller_app.UIDashboardSaleManagerController;
import data.EventDetail;
import data.Events;
import data.User;
import data.VEvent;
import data_modifier.UserModifier;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
    String eventID, productID, Name, disc, startd, endd, mfgd, expd, endEvent, start, nameEvent;
    int count = 0, countName = 0;

    @FXML
    private Label createlabel;
    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtDiscount;

    @FXML
    private TableColumn<VEvent, String> eventName;
    @FXML
    private TableColumn<VEvent, String> productId;
    @FXML
    private TableColumn<VEvent, String> discount;
    @FXML
    private TableColumn<VEvent, String> startDate;
    @FXML
    private TableColumn<VEvent, String> endDate;

    @FXML
    private TableView<VEvent> createEventTable;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> eventIdCombobox;
    @FXML
    private ComboBox<String> productIdCombobox;
    @FXML
    private DatePicker mfgDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private TableColumn<VEvent, String> userId;
    @FXML
    private TableColumn<VEvent, String> mfgDate;
    @FXML
    private TableColumn<VEvent, String> expDate;
    @FXML
    private HBox createEvent;
    @FXML
    private HBox createEventDetail;
    @FXML
    private Label createlabel1;
    @FXML
    private TableColumn<VEvent, String> eventId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getShow();
            setValueEventIdComboBox(endEvent);
            setValueProductIdComboBox(endEvent);
        } catch (SQLException ex) {
            Logger.getLogger(CreateEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getShow() throws SQLException {
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
        createEventTable.setItems(oList);
    }

    // Hien productId chua co su kien hoac su kien het han
    private void setValueProductIdComboBox(String endEvent) throws SQLException {
        endEvent = String.valueOf(LocalDate.now());
        ObservableList<String> oList = new VEventModifier().getProductIdEvent(endEvent);
        
        if (!oList.isEmpty()) {
            productIdCombobox.setItems(oList);
            productIdCombobox.setValue(oList.get(0));
            proID = productIdCombobox.getValue();
            productIdCombobox.setOnAction((t) -> {
                proID = productIdCombobox.getValue();
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Out of stock for event creation!");
            alert.showAndWait();

        }
    }
//    private void setValueProductIdComboBox() throws SQLException {
//        ObservableList<String> oList = new VEventModifier().getListProductId();
//        productIdCombobox.setItems(oList);
//        productIdCombobox.setValue(oList.get(0));
//
//        proID = productIdCombobox.getValue();
//
//        productIdCombobox.setOnAction((t) -> {
//            proID = productIdCombobox.getValue();
//        });
//    }

    // chi hien su kien chua het han
    private void setValueEventIdComboBox(String endEvent) throws SQLException {
        endEvent = String.valueOf(LocalDate.now());
        ObservableList<String> oList = new VEventModifier().getListEventIdDate(endEvent);
        
        if (!oList.isEmpty()) {
        eventIdCombobox.setItems(oList);
        eventIdCombobox.setValue(oList.get(0));
        evtID = eventIdCombobox.getValue();
        eventIdCombobox.setOnAction((t) -> {
            evtID = eventIdCombobox.getValue();        
                    });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All events have expired!");
            alert.showAndWait();

        }
        
    }

    @FXML
    private void startDate(ActionEvent event) {
        LocalDate start = startDatePicker.getValue();
        startd = String.valueOf(start);
    }

    @FXML
    private void endDate(ActionEvent event) {
        LocalDate end = startDatePicker.getValue();
        endd = String.valueOf(end);
    }

    @FXML
    private void eventId(ActionEvent event) {
//        eventIdCombobox.setValue(Name);
        eventID = eventIdCombobox.getValue();
    }

    @FXML
    private void productId(ActionEvent event) {
        productID = productIdCombobox.getValue();
    }

    @FXML
    private void mfgDate(ActionEvent event) {
        LocalDate mfg = mfgDatePicker.getValue();
        mfgd = String.valueOf(mfg);
    }

    @FXML
    private void expDate(ActionEvent event) {
        LocalDate exp = expDatePicker.getValue();
        expd = String.valueOf(exp);
    }

    boolean checkName(String Name) throws SQLException {
        ObservableList olistName = new VEventModifier().getListEventName();

        for (int i = 0; i < olistName.size(); i++) {

            if (Name.equals(olistName.get(i)) == true) {
                countName++;
                break;
            }
        }
        if (countName != 0) {
            countName = 0;
            return false;
        }
        return true;
    }

    @FXML
    private void createEventClick(MouseEvent event) throws SQLException {

        lUserId = UIDashboardSaleManagerController.gUserId;
        User user = new User();
        user = new UserModifier().getUser(lUserId);
        userIdTF = user.getPersonId();
        Name = txtEventName.getText();
        if (txtEventName.getText().isEmpty() || checkName(Name) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Event already exists!");
            alert.showAndWait();
        } else {
            if ((endDatePicker.getValue() == null) || (startDatePicker.getValue() == null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Data");
                alert.showAndWait();

            } else {

                LocalDate now = LocalDate.now();
                LocalDate checkStart = startDatePicker.getValue();
                LocalDate checkEnd = endDatePicker.getValue();

                // kiem tra co event trung ngay bat dau khong 
                ObservableList olist = new VEventModifier().getListStartEvent();

                for (int i = 0; i < olist.size(); i++) {
                    start = String.valueOf(olist.get(i));
                    LocalDate startEvent = LocalDate.parse(start);

                    if (checkStart.compareTo(startEvent) == 0) {
                        count++;
                        break;
                    }
                }

                if (checkStart.compareTo(now) < 0 || checkEnd.compareTo(checkStart) <= 0 || count != 0) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Please re-enter the date!");
                    alert.showAndWait();
                    count = 0;

                } else {

                    Events events = new Events();
                    events.setUserId(userIdTF);
                    events.setEventName(Name);
                    events.setStartDate(startd);
                    events.setEndDate(endd);

                    if (new VEventModifier().getCreateEvents(events)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Notification");
                        alert.setHeaderText("Success");
                        alert.setContentText("Event created successfully!");
                        alert.showAndWait();

                    }
                }
            }
        }
        txtEventName.setText(null); //tenbiendata
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        getShow();
        setValueEventIdComboBox(endEvent);
    }

    @FXML
    private void createEventDetailClick(MouseEvent event) throws SQLException {
        
        
        if (txtDiscount.getText().isEmpty() || (mfgDatePicker.getValue() == null) || (expDatePicker.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();

        } else {
            LocalDate now = LocalDate.now();
            LocalDate checkStart = mfgDatePicker.getValue();
            LocalDate checkEnd = expDatePicker.getValue();
            if (checkEnd.compareTo(checkStart) <= 0 || checkEnd.compareTo(now) < 0) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please re-enter the date!");
                alert.showAndWait();

            } else {
                
                disc = txtDiscount.getText();
                EventDetail eventDetail = new EventDetail();
                eventDetail.setEventId(evtID);
                eventDetail.setProductId(proID);
                eventDetail.setDiscount(disc);
                eventDetail.setMfgDate(mfgd);
                eventDetail.setExpDate(expd);

                if (new VEventModifier().getCreateEventDetail(eventDetail)) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notification");
                    alert.setHeaderText("Success");
                    alert.setContentText("Event details created successfully!");
                    alert.showAndWait();
                }
            }
        }

        eventIdCombobox.setValue(null);
        productIdCombobox.setValue(null);
        txtDiscount.setText(null);
        mfgDatePicker.setValue(null);
        expDatePicker.setValue(null);
        getShow();
        setValueProductIdComboBox(endEvent);
        

    }

}
