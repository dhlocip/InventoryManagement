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
public class UpdateEventController implements Initializable {

    String proID, evtID;
    String updateEventName;
    String updateDiscount;
    String updateStartDate, updateMfgDate;
    String updateEndDate, updateExpDate;
    String updateProductId, updateEventIdDetail;
    String lUserId;
    String userIdTF;
    @FXML
    private TextField txtEventName;
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private HBox updateEvent;
    @FXML
    private ComboBox<String> eventIdCombobox;
    @FXML
    private ComboBox<String> productIdCombobox;
    @FXML
    private TextField txtDiscount;
    @FXML
    private HBox updateEventDetail;

    @FXML
    private DatePicker txtMfgDate;
    @FXML
    private DatePicker txtExpDate;
    @FXML
    private TableView<Events> updateEventTable;
    @FXML
    private TableColumn<Events, String> eventName;
    @FXML
    private TableColumn<Events, String> startDate;
    @FXML
    private TableColumn<Events, String> endDate;
    @FXML
    private TableView<EventDetail> updateEventDetailTable;
    @FXML
    private TableColumn<EventDetail, String> eventIdDetail;
    @FXML
    private TableColumn<EventDetail, String> productId;
    @FXML
    private TableColumn<EventDetail, String> discount;
    @FXML
    private TableColumn<EventDetail, String> mfgDate;
    @FXML
    private TableColumn<EventDetail, String> expDate;
    @FXML
    private HBox upDateEventDetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getShowEvent();
            getShowEventDetail();
            setValueEventIdComboBox();
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getShowEvent() throws SQLException {
        ObservableList<Events> oList = new VEventModifier().getVEventsInfo();
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        updateEventTable.setItems(oList);

    }

    private void getShowEventDetail() throws SQLException {
        ObservableList<EventDetail> oList = new VEventModifier().getEventDetailInfo();
        eventIdDetail.setCellValueFactory(new PropertyValueFactory<>("eventIdDetail"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        updateEventDetailTable.setItems(oList);

    }

    private void setValueProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListEventId();

        productIdCombobox.setItems(oList);
        productIdCombobox.setValue(oList.get(0));

        proID = productIdCombobox.getValue();

        productIdCombobox.setOnAction((t) -> {
            proID = productIdCombobox.getValue();
        });

    }

    private void setValueEventIdComboBox() throws SQLException {

        ObservableList<String> oList = new VEventModifier().getListEventId();
        eventIdCombobox.setItems(oList);
        eventIdCombobox.setValue(oList.get(0));

        evtID = eventIdCombobox.getValue();

        eventIdCombobox.setOnAction((t) -> {
            evtID = eventIdCombobox.getValue();
        });

    }

    @FXML
    private void updateEventClick(MouseEvent event) throws SQLException {
        Events item = updateEventTable.getSelectionModel().getSelectedItem();
        if (item == null || txtEventName.getText().isEmpty()
                || (txtEndDate.getValue() == null) || (txtStartDate.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the event to update");
            alert.showAndWait();

        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
            User user = new User();
            user = new UserModifier().getUser(lUserId);
            userIdTF = user.getPersonId();

            updateEventName = txtEventName.getText();

            Events events = new Events();
            events.setEventName(updateEventName);
            events.setStartDate(updateStartDate);
            events.setEndDate(updateEndDate);
            events.setEventId(item.getEventId());

            if (new VEventModifier().getUpdateEvent(events)) {
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
    private void updateEventDetailClick(MouseEvent event) throws SQLException {
        EventDetail item = updateEventDetailTable.getSelectionModel().getSelectedItem();
        if ( item == null || txtDiscount.getText().isEmpty()|| (txtMfgDate.getValue() == null) || (txtExpDate.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the eventDetail to update");
            alert.showAndWait();

        } else {

            updateDiscount = txtDiscount.getText();
            EventDetail eventDetail = new EventDetail();
            eventDetail.setProductId(item.getProductId());
            eventDetail.setDiscount(updateDiscount);
            eventDetail.setMfgDate(updateMfgDate);
            eventDetail.setExpDate(updateExpDate);
            eventDetail.setEventIdDetail(evtID);
           
            if (new VEventModifier().getUpdateEventDetail(eventDetail)) {
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
    private void getUpdateEventMouseClickTable(MouseEvent event) {
        Events item = updateEventTable.getSelectionModel().getSelectedItem();

        if (item != null) {
            txtEventName.setText(item.getEventName()); //tenbiendata

            LocalDate start = LocalDate.parse(item.getStartDate());
            txtStartDate.setValue(start);

            LocalDate end = LocalDate.parse(item.getEndDate());
            txtEndDate.setValue(end);

        }

    }

    @FXML
    private void GetUpdateEventDetailMouseClickTable(MouseEvent event) {
        EventDetail item = updateEventDetailTable.getSelectionModel().getSelectedItem();
        if (item != null) {
            eventIdCombobox.setValue(item.getEventIdDetail());
            productIdCombobox.setValue(item.getProductId());
            txtDiscount.setText(item.getDiscount());

            LocalDate mfg = LocalDate.parse(item.getMfgDate());
            txtMfgDate.setValue(mfg);

            LocalDate exp = LocalDate.parse(item.getExpDate());
            txtExpDate.setValue(exp);

        }
    }

    @FXML
    private void startDateAction(ActionEvent event) {

        LocalDate start = txtStartDate.getValue();
        updateStartDate = String.valueOf(start);
    }

    @FXML
    private void endDateAction(ActionEvent event) {
        LocalDate end = txtEndDate.getValue();
        updateEndDate = String.valueOf(end);
    }

    @FXML
    private void eventIdAction(ActionEvent event) {
        updateEventIdDetail = eventIdCombobox.getValue();
    }

    @FXML
    private void productIdAction(ActionEvent event) {
        updateProductId = productIdCombobox.getValue();
    }

    @FXML
    private void mfgDateAction(ActionEvent event) {
        LocalDate mfd = txtMfgDate.getValue();
        updateMfgDate = String.valueOf(mfd);
    }

    @FXML
    private void expDateAction(ActionEvent event) {
        LocalDate exp = txtExpDate.getValue();
        updateExpDate = String.valueOf(exp);
    }

}
