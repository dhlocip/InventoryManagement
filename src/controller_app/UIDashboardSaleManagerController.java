/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_app;

import data_modifier.NewRequestModilfier;
import data_modifier.RequestModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UIDashboardSaleManagerController implements Initializable {

    public static String gUserId;
    public static String gFullName;
    public static String gPosition;

    @FXML
    private BorderPane homePane;
    @FXML
    private VBox supMenuBox;
    @FXML
    private VBox menuBox;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label RequestNumber;
    @FXML
    private Label newRequestNumber;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideSupMenu(false);
        try {
            NumberRequest();
            NumberNewRequests();
        } catch (SQLException ex) {
            Logger.getLogger(UIDashboardSaleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void setValueForVariableStatic(String userId, String fullName, String position) {
        gUserId = userId;
        userIdLabel.setText(userId);
        gFullName = fullName;
        fullNameLabel.setText(fullName);
        gPosition = position;
        positionLabel.setText(position);

    }

    private void hideMenu(boolean value) {
        menuBox.setVisible(value);
        menuBox.managedProperty().bind(menuBox.visibleProperty());
    }

    private void hideSupMenu(boolean value) {
        supMenuBox.setVisible(value);
        supMenuBox.managedProperty().bind(supMenuBox.visibleProperty());
    }

    private void setCenterBox(String value) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_sale_manager/" + value + ".fxml"));
        homePane.setCenter(root);
    }

    private void setCenterBoxFromViewAdmin(String value) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/" + value + ".fxml"));
        homePane.setCenter(root);
    }

    @FXML
    private void supMenuClicked(MouseEvent event) {
        hideMenu(true);
        hideSupMenu(false);
    }

    @FXML
    private void menuClicked(MouseEvent event) {
        hideSupMenu(true);
        hideMenu(false);
    }

    @FXML
    private void viewEventClicked(MouseEvent event) throws IOException {
        setCenterBox("ViewEvent");
//        System.out.println(gPosition);
    }

    @FXML
    private void createEventClicked(MouseEvent event) throws IOException {
        setCenterBox("CreateEvent");

    }

    @FXML
    private void updateEventClicked(MouseEvent event) throws IOException {
        setCenterBox("UpdateEvent");

    }

    @FXML
    private void deleteEventClicked(MouseEvent event) throws IOException {
        setCenterBox("DeleteEvent");

    }

    @FXML
    private void requestClicked(MouseEvent event) throws IOException {
        setCenterBox("Request");
        try {
            NumberRequest();
            
        } catch (SQLException ex) {
            Logger.getLogger(UIDashboardSaleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void NumberRequest() throws SQLException{
        ObservableList<String> oList = new RequestModifier().getNumberRequest();
            RequestNumber.setText(oList.get(0));
        
    }

    @FXML
    private void newRequestClicked(MouseEvent event) throws IOException {
        setCenterBox("NewRequest");
        try {
            NumberNewRequests();
        } catch (SQLException ex) {
            Logger.getLogger(UIDashboardSaleManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void NumberNewRequests() throws SQLException{
    ObservableList<String> oList = new NewRequestModilfier().getNumberNewRequest();
    newRequestNumber.setText(oList.get(0));
    }
    

    @FXML
    private void editProfileClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("EditProfile");
    }

    @FXML
    private void changePasswordClicked(MouseEvent event) throws IOException {
        setCenterBoxFromViewAdmin("ChangePassword");
    }

    @FXML
    private void byDateClicked(MouseEvent event) throws IOException {
        setCenterBox("StatisticByDate");

    }

    @FXML
    private void byCanceledBillClicked(MouseEvent event) throws IOException {
        setCenterBox("StatisticByCancel");

    }

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
        gUserId = null;
        gFullName = null;
        gPosition = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/UILogIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign In");
        stage.show();
    }

}
