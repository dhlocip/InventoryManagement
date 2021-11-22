/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import controller_app.UIDashboardSaleManagerController;
import data.VRequest;
import data_modifier.RequestModifier;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class RequestController implements Initializable {
    
    String newRequestId, newProductName , newStatusVerify;
     String requestID,  numberRequest;
    
    @FXML
    private TableView<VRequest> RequestTable;

    @FXML
    private TableColumn<VRequest, String> requestId;
    @FXML
    private TableColumn<VRequest, String> productId;
    @FXML
    private TableColumn<VRequest, Integer> quantity;
    @FXML
    private TableColumn<VRequest, String> userId;
    @FXML
    private TableColumn<VRequest, String> startDate;
    @FXML
    private TableColumn<VRequest, String> statusVerify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getShow() throws SQLException {

        ObservableList<VRequest> oList = new RequestModifier().getRequestInfo();
        requestId.setCellValueFactory(new PropertyValueFactory<>("requestId")); //tenbiendata
        productId.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("productId")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        statusVerify.setCellValueFactory(new PropertyValueFactory<>("statusVerify")); //tenbiendata

        RequestTable.setItems(oList);

    }

    @FXML
    private void getRequest(MouseEvent event) throws SQLException {
    VRequest item = RequestTable.getSelectionModel().getSelectedItem();
         
        if (item != null ) {
            newRequestId = item.getRequestId();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Would you like to browse this request?");
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();

            if(result.get()== buttonTypeYes){
                newStatusVerify = "YES";
                new RequestModifier().getRequestUpdate(newStatusVerify, newRequestId);
                

            } else if(result.get()== buttonTypeNo){
                newStatusVerify = "NO";
                new RequestModifier().getRequestUpdate(newStatusVerify, newRequestId);
                
            }

        } else {        
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please select the request to review!");
                alert.showAndWait();
        }
        getShow();
        
   

    }

}
