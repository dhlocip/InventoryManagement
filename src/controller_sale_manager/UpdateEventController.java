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
    private TextField txtDiscount;
    @FXML
    private TableView<VEvent> updateEventTable;

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
    @FXML
    private HBox upDateEvent;
    @FXML
    private HBox upDateEventDetail;
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
    private TableColumn<VEvent, String> eventId;
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
            // TODO
            getShow();
            setValueProductIdComboBox();
            setValueEventIdComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEventController.class.getName()).log(Level.SEVERE, null, ex);
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
        updateEventTable.setItems(oList);
    }

    private void setValueProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListProductId();
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
    private void startDate(ActionEvent event) {
        LocalDate start = startDatePicker.getValue();
        updateStartDate = String.valueOf(start);

    }

    @FXML
    private void endDate(ActionEvent event) {
        LocalDate end = endDatePicker.getValue();
        updateEndDate = String.valueOf(end);
    }

    @FXML
    private void updateEventsClick(MouseEvent event) throws SQLException {

        VEvent item = updateEventTable.getSelectionModel().getSelectedItem();

        if (item == null || txtEventName.getText().isEmpty()
                || (endDatePicker.getValue() == null) || (startDatePicker.getValue() == null)) {
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

                txtEventName.setText(null); //tenbiendata
                startDatePicker.setValue(null);
                endDatePicker.setValue(null);

            }
        }
        getShow();
        setValueEventIdComboBox();
    }

    @FXML
    private void eventId(ActionEvent event) {
        updateEventIdDetail = eventIdCombobox.getValue();
    }

    @FXML
    private void productId(ActionEvent event) {
        updateProductId = productIdCombobox.getValue();
    }

    @FXML
    private void mfgDate(ActionEvent event) {
        LocalDate mfg = mfgDatePicker.getValue();
        updateMfgDate = String.valueOf(mfg);
    }

    @FXML
    private void expDate(ActionEvent event) {
        LocalDate exp = endDatePicker.getValue();
        updateExpDate = String.valueOf(exp);
    }

    @FXML
    private void updateEventDetailClick(MouseEvent event) throws SQLException {

        VEvent item = updateEventTable.getSelectionModel().getSelectedItem();
        if (item == null || txtDiscount.getText().isEmpty() || (mfgDatePicker.getValue() == null) || (expDatePicker.getValue() == null)) {
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
            eventDetail.setEventId(evtID);

            if (new VEventModifier().getUpdateEventDetail(eventDetail)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();

                eventIdCombobox.setValue(null);
                productIdCombobox.setValue(null);
                txtDiscount.setText(null);
                mfgDatePicker.setValue(null);
                expDatePicker.setValue(null);
            }
        }
        getShow();

    }

    @FXML
    private void updateEventTableClick(MouseEvent event) {

        VEvent item = updateEventTable.getSelectionModel().getSelectedItem();
        if (item != null) {
            txtEventName.setText(item.getEventName()); //tenbiendata
            LocalDate start = LocalDate.parse(item.getStartDate());
            startDatePicker.setValue(start);

            LocalDate end = LocalDate.parse(item.getEndDate());
            endDatePicker.setValue(end);

            if (item.getProductId() != null) {
                eventIdCombobox.setValue(item.getEventId());
                productIdCombobox.setValue(item.getProductId());
                txtDiscount.setText(item.getDiscount());
                LocalDate mfg = LocalDate.parse(item.getMfgDate());
                mfgDatePicker.setValue(mfg);
                LocalDate exp = LocalDate.parse(item.getExpDate());
                expDatePicker.setValue(exp);

            } else {
                eventIdCombobox.setValue(null);
                productIdCombobox.setValue(null);
                txtDiscount.setText(null);
                mfgDatePicker.setValue(null);
                expDatePicker.setValue(null);

            }

        }
    }
}
