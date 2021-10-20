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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UpdateEventController implements Initializable {

    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtDiscount;
    @FXML
    private DatePicker txtStartDate;
    @FXML
    private TableView<VEvent> updateEventTable;
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
    @FXML
    private ComboBox<?> comboboxProductId;
    @FXML
    private DatePicker txtEndDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateEventController.class.getName()).log(Level.SEVERE, null, ex);
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
        updateEventTable.setItems(oList);
    }

    
    @FXML
    private void updateEventClicked(MouseEvent event) {        
        System.out.println(txtStartDate.getValue());
        
    }
    
     @FXML
    private void getDelete(ActionEvent event) {     
              
    if(updateEventTable.getSelectionModel().getSelectedItem() != null){
        
    
    };
    
    
        
    
    }
    
    
    
    
}
//
// @FXML
//    private void updateProductClicked(MouseEvent event) throws SQLException {
//
//        if (lProductTypeId != null && isPriceRight() && isUnitRight() && isProNameRight()) {
//            Products items = productDetailTableView.getSelectionModel().getSelectedItem();
//            lPrice = Double.parseDouble(priceTextField.getText());
//            lUnit = unitTextField.getText();
//            lProductName = productNameTextField.getText();
//            lProductTypeId = productTypeIdComboBox.getValue();
//
//            if (items != null) {
//                items.setProductTypeId(lProductTypeId);
//                items.setProductName(lProductName);
//                items.setUnit(lUnit);
//                items.setPrice(lPrice);
//
//                if (new ProductsModifier().updateProducts(items)) {
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Notification");
//                    alert.setHeaderText("Success");
//                    alert.setContentText("update product is successfully.");
//                    alert.showAndWait();
//
////                reload table products 
//                    getProductsInfo();
//                }
//            }
//
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Notification");
//            alert.setHeaderText("Error");
//            alert.setContentText("Please click to item.");
//            alert.showAndWait();
//
//            if (!isPriceRight()) {
//                hideErrorOfPrice(true);
//                errorOfPrice.setText("Price is invalid.");
//            }
//
//            if (!isUnitRight()) {
//                hideErrorOfUnit(true);
//                errorOfUnit.setText("Unit is invalid.");
//            }
//
//            if (!isProNameRight()) {
//                hideErrorOfProName(true);
//                errorOfProductName.setText("ProductName is invalid.");
//            }
//
//            if (lProductTypeId == null) {
//                hideErrorOfProTypeId(true);
//                errorOfProductTypeId.setText("ProductTypeId can't empty.");
//            }
//        }
//    }
//
//    @FXML
//    private void searchReleased(KeyEvent event) throws SQLException {
//        getProductsInfoWhenSearch(searchTextField.getText());
//    }
