/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_sale_manager;

import data.VNewRequest;
import data_modifier.NewRequestModilfier;
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
public class NewRequestController implements Initializable {

    String userID, newStatusVerify;

    @FXML
    private TableView<VNewRequest> newRequestTable;
    @FXML
    private TableColumn<VNewRequest, String> newRequestId;
    @FXML
    private TableColumn<VNewRequest, String> newproductName;
    @FXML
    private TableColumn<VNewRequest, Integer> quantity;
    @FXML
    private TableColumn<VNewRequest, String> userId;
    @FXML
    private TableColumn<VNewRequest, String> startDate;
    @FXML
    private TableColumn<VNewRequest, String> statusVerify;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            getShow();
        } catch (SQLException ex) {
            Logger.getLogger(NewRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getShow() throws SQLException {

        ObservableList<VNewRequest> oList = new NewRequestModilfier().getNewRequestInfo();
        newRequestId.setCellValueFactory(new PropertyValueFactory<>("newRequestId")); //tenbiendata
        newproductName.setCellValueFactory(new PropertyValueFactory<>("newProductName")); //tenbiendata
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); //tenbiendata
        userId.setCellValueFactory(new PropertyValueFactory<>("userId")); //tenbiendata
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate")); //tenbiendata
        statusVerify.setCellValueFactory(new PropertyValueFactory<>("statuVerify")); //tenbiendata

        newRequestTable.setItems(oList);

    }

    @FXML
    private void getNewRequest(MouseEvent event) throws SQLException {

        VNewRequest item = newRequestTable.getSelectionModel().getSelectedItem();
        userID = item.getUserId();
        if (item != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the Event.");
            ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                newStatusVerify = "YES";
                new NewRequestModilfier().getNewRequestUpdate(newStatusVerify, userID);
                getShow();

            } else if (result.get() == buttonTypeNo) {
                newStatusVerify = "NO";
                new NewRequestModilfier().getNewRequestUpdate(newStatusVerify, userID);
                getShow();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select the event to update");
            alert.showAndWait();

        }
    }

}
