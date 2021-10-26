/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data.User;
import data_modifier.UserModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UILogInController implements Initializable {

    String lUserId;
    String lFullName;
    String lPosition;

    @FXML
    private BorderPane homeBox;
    @FXML
    private HBox errorHBox;
    @FXML
    private TextField userNameTF;
    @FXML
    private PasswordField passwordTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        hideError(false);

    }

    private void hideError(boolean value) {
        errorHBox.setVisible(value);
        errorHBox.managedProperty().bind(errorHBox.visibleProperty());
    }

    private boolean isUserNameRight() {
        String tmp = userNameTF.getText();
        return tmp.matches("^[a-zA-Z]+[_.]?[\\w]*") && tmp.length() >= 5;
    }

    @FXML
    private void userNameReleased(KeyEvent event) {
        hideError(false);
    }

    private boolean isPasswordRight() {
        String tmp = passwordTF.getText();
        return tmp.matches("^[\\w_.!@#$%^*]+") && tmp.length() >= 8;
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
        hideError(false);
    }

    @FXML
    private void forgotPasswordClicked(MouseEvent event) {
    }

    private void nextToDashboard(String permission, ActionEvent event) throws IOException {
        if (permission.equalsIgnoreCase("admin")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_app/UIDashboardAdmin.fxml"));
            Parent root = loader.load();

            UIDashboardAdminController control = loader.getController();
            control.setValueForVariableStatic(lUserId, lFullName, lPosition);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Admin");
            stage.show();

        } else if (permission.equalsIgnoreCase("inventory manager")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_app/UIDashboardInventoryManager.fxml"));
            Parent root = loader.load();

            UIDashboardInventoryManagerController control = loader.getController();
            control.setValueForVariableStatic(lUserId, lFullName, lPosition);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Inventory Manager");
            stage.show();

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_app/UIDashboardSaleManager.fxml"));
            Parent root = loader.load();

            UIDashboardSaleManagerController control = loader.getController();
            control.setValueForVariableStatic(lUserId, lFullName, lPosition);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Sale Manager");
            stage.show();

        }
    }

    @FXML
    private void signInAction(ActionEvent event) throws IOException, SQLException {
        if (isUserNameRight() && isPasswordRight()) {
            hideError(false);
            User user = new User();
            user = new UserModifier().signIn(userNameTF.getText(), passwordTF.getText());
            if (user.getPosition() != null) {
                lUserId = user.getPersonId();
                lFullName = user.getFullName();
                lPosition = user.getPosition();
                if (lPosition.equalsIgnoreCase("admin")) {
                    nextToDashboard(lPosition, event);

                } else if (lPosition.equalsIgnoreCase("inventory manager")) {
                    nextToDashboard(lPosition, event);

                } else {
                    nextToDashboard(lPosition, event);

                }
            } else {
                hideError(true);
            }

        } else {
            hideError(true);

        }
    }

}
