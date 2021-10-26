/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.VEvent;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class DeleteEventController implements Initializable {

    String eventID;

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
    private TableColumn<VEvent, String> eventId;
    @FXML
    private TableView<VEvent> deleteEvent;
    @FXML
    private HBox search;
    @FXML
    private ComboBox<String> eventIdCombobox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getShow();

           setValueEventIdComboBox();

        } catch (SQLException ex) {
            Logger.getLogger(DeleteEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getShow() throws SQLException {

        ObservableList<VEvent> oList = new VEventModifier().getEventInfo();
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata    
        deleteEvent.setItems(oList);
    }

    private void setValueEventIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListEventId();
        eventIdCombobox.setItems(oList);
        eventIdCombobox.setValue(oList.get(0));

        eventID = eventIdCombobox.getValue();

        eventIdCombobox.setOnAction((t) -> {
            eventID = eventIdCombobox.getValue();
        });

    }

    @FXML
    private void getDelete(MouseEvent event) throws SQLException {


        VEvent item = deleteEvent.getSelectionModel().getSelectedItem();
        if (item != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the Event.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (new VEventModifier().deleteEventDetailByEventId(item.getEventId())
                        && new VEventModifier().deleteEventByEventId(item.getEventId())) {
                    new VEventModifier().deleteEventDetailByEventId(item.getEventId());
                    getShow();
                }

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null");
        }

    }

    public void getEventByEventId(String eventID) throws SQLException {

        ObservableList<VEvent> oList = new VEventModifier().getInfoByEventId(eventID);
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        eventId.setCellValueFactory(new PropertyValueFactory<>("eventId")); //tenbiendata    
        deleteEvent.setItems(oList);
    }

    @FXML
    private void getFind(MouseEvent event) throws SQLException {
        
        getEventByEventId(eventIdCombobox.getValue());
        
    }


    
}
