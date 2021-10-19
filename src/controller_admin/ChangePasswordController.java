/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data_modifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ChangePasswordController implements Initializable {

    String lUserId;
    String lCurrentPassword;
    String lNewPassword;
    String lRetypeNewPassword;

    @FXML
    private PasswordField currentPasswordPF;
    @FXML
    private PasswordField newPasswordPF;
    @FXML
    private PasswordField retypeNewPasswordPF;
    @FXML
    private Label errorCurrentPassword;
    @FXML
    private Label errorNewPassword;
    @FXML
    private Label errorRetypeNewPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        get userId
        if (UIDashboardAdminController.gPosition != null
                && UIDashboardInventoryManagerController.gPosition == null
                && UIDashboardSaleManagerController.gPosition == null) {
            lUserId = UIDashboardAdminController.gUserId;

        } else if (UIDashboardAdminController.gPosition == null
                && UIDashboardInventoryManagerController.gPosition != null
                && UIDashboardSaleManagerController.gPosition == null) {

            lUserId = UIDashboardInventoryManagerController.gUserId;
        } else {
            lUserId = UIDashboardSaleManagerController.gUserId;
        }

        hideErrorOfCurrentPassword(false);
        hideErrorOfNewPassword(false);
        hideErrorOfRetypeNewPassword(false);

    }

    private void hideErrorOfCurrentPassword(boolean value) {
        errorCurrentPassword.setVisible(value);
        errorCurrentPassword.managedProperty().bind(errorCurrentPassword.visibleProperty());
    }

    private void hideErrorOfNewPassword(boolean value) {
        errorNewPassword.setVisible(value);
        errorNewPassword.managedProperty().bind(errorNewPassword.visibleProperty());
    }

    private void hideErrorOfRetypeNewPassword(boolean value) {
        errorRetypeNewPassword.setVisible(value);
        errorRetypeNewPassword.managedProperty().bind(errorRetypeNewPassword.visibleProperty());
    }

    private boolean isCurrentPassRight() {
        String tmp = currentPasswordPF.getText();
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void currentPasswordReleased(KeyEvent event) throws SQLException {
        lCurrentPassword = currentPasswordPF.getText();

        if (isCurrentPassRight()
                && lCurrentPassword.equalsIgnoreCase(new UserModifier().getUser(lUserId).getPassword())) {
            hideErrorOfCurrentPassword(false);
        } else {
            hideErrorOfCurrentPassword(true);
            errorCurrentPassword.setText("\"current password\" is invalid.");
        }
    }

    private boolean isNewPassRight() {
        String tmp = newPasswordPF.getText();
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void newPasswordReleased(KeyEvent event) throws SQLException {
        lNewPassword = newPasswordPF.getText();

        if (isNewPassRight() && !lNewPassword.equalsIgnoreCase(new UserModifier().getUser(lUserId).getPassword())) {
            hideErrorOfNewPassword(false);
        } else {
            hideErrorOfNewPassword(true);
            errorNewPassword.setText("\"new password\" is invalid.");
        }
    }

    private boolean isRetypeNewPassRight() {
        String tmp = retypeNewPasswordPF.getText();
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void retypePasswordReleased(KeyEvent event) {
        lNewPassword = newPasswordPF.getText();
        lRetypeNewPassword = retypeNewPasswordPF.getText();

        if (isNewPassRight() && lNewPassword.equalsIgnoreCase(lRetypeNewPassword)) {
            hideErrorOfRetypeNewPassword(false);
        } else {
            hideErrorOfRetypeNewPassword(true);
            errorRetypeNewPassword.setText("\"retype new password\" is invalid.");
        }
    }

    @FXML
    private void updateProfileClicked(MouseEvent event) throws SQLException {
        if (isCurrentPassRight() && isNewPassRight() && isRetypeNewPassRight()) {
            if (new UserModifier().changePassword(lUserId, lRetypeNewPassword)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Change password is successfully.");
                alert.showAndWait();
                currentPasswordPF.setText("");
                newPasswordPF.setText("");
                retypeNewPasswordPF.setText("");
            }
        } else {
            hideErrorOfCurrentPassword(true);
            errorCurrentPassword.setText("\"current password\" is invalid.");
            hideErrorOfNewPassword(true);
            errorNewPassword.setText("\"new password\" is invalid.");
            hideErrorOfRetypeNewPassword(true);
            errorRetypeNewPassword.setText("\"retype new password\" is invalid.");
        }
    }

}
