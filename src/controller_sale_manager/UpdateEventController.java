/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.EventDetail;
import data.Events;
import data.VEvent;
import data_modifier.VEventModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    String proID;
    String updateEventName;
    String updateDiscount;
    String updateStartDate;
    String updateEndDate;
    String updateProductId;

    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtDiscount;
    private DatePicker txtStartDate;
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
    private DatePicker txtEndDate;
    private ComboBox<String> productICombobox;
    @FXML
    private HBox upDateEvent;
    @FXML
    private HBox upDateEventDetail;
    @FXML
    private ComboBox<?> eventIDCombobox;
    @FXML
    private ComboBox<?> productIdCombobox;
    @FXML
    private DatePicker mfgDatePicker;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableColumn<?, ?> eventName1;
    @FXML
    private TableColumn<?, ?> userId1;
    @FXML
    private TableColumn<?, ?> startDate1;
    @FXML
    private TableColumn<?, ?> endDate1;
    @FXML
    private TableView<?> updateEventTable1;
    @FXML
    private TableColumn<?, ?> eventName11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getShow();
            setValueProductIdComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getShow() throws SQLException {

        ObservableList<VEvent> oList = new VEventModifier().getEventInfo();
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        discount.setCellValueFactory(new PropertyValueFactory<>("discount")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate")); //tenbiendata
        updateEventTable.setItems(oList);
    }

    private void setValueProductIdComboBox() throws SQLException {
        ObservableList<String> oList = new VEventModifier().getListProductId();
        productICombobox.setItems(oList);
        productICombobox.setValue(oList.get(0));

        proID = productICombobox.getValue();

        productICombobox.setOnAction((t) -> {
            proID = productICombobox.getValue();
        });

    }

    private void updateEventMouseClicked(MouseEvent event) {
        VEvent item = updateEventTable.getSelectionModel().getSelectedItem();
        
        if (item != null) {
            txtEventName.setText(item.getEventName()); //tenbiendata
            txtDiscount.setText(item.getDiscount());

            LocalDate start = LocalDate.parse(item.getStartDate());
            txtStartDate.setValue(start);

            LocalDate end = LocalDate.parse(item.getEndDate());
            txtEndDate.setValue(end);
            productICombobox.setValue(item.getProductId());
        }
    }

   

    @FXML
    private void updateEventClick(MouseEvent event) {

    }

    @FXML
    private void updateEventDetailClick(MouseEvent event) {
    }

    @FXML
    private void updateEventDetailClickTable(MouseEvent event) {
    }

    @FXML
    private void updateEventClickTable(MouseEvent event) {
    }

}
