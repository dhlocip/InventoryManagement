/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data_modifier.UserModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ip
 */
public class NewPasswordController implements Initializable {

    String lEmail;
    
    @FXML
    private BorderPane homeBox;
    @FXML
    private TextField newPasswordTF;
    @FXML
    private Label errorPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        hideErrorOfPassword(false);
    }
    
    public void setEmail(String email) {
        lEmail = email;
    }

    private void hideErrorOfPassword(boolean value) {
        errorPassword.setVisible(value);
        errorPassword.managedProperty().bind(errorPassword.visibleProperty());
    }

    private boolean isNewPasswordRight() {
        String tmp = newPasswordTF.getText();
        return tmp.matches("^[\\w_.!@#$%^*]+") && tmp.length() >= 8;
    }

    private void checkPassword() {
        if (isNewPasswordRight()) {
            hideErrorOfPassword(false);
        } else {
            hideErrorOfPassword(true);
            errorPassword.setText("\"password\" is invalid.");
        }
    }

    @FXML
    private void newPasswordReleased(KeyEvent event) {
        checkPassword();
    }
    
    private static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    @FXML
    private void continueAction(ActionEvent event) throws IOException, SQLException {
        if (isNewPasswordRight()) {
            // update password
            new UserModifier().updatePassword(newPasswordTF.getText(), lEmail);
            
            // change token
            String newToken = getRandomNumberString();
            new UserModifier().updateToken(newToken, lEmail);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("Change password is successfully. \nClick OK to return to the \'Sign in\' page.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("/view_admin/UILogIn.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Sign In");
                stage.show();
            }
        } else {
            checkPassword();
        }
    }

}
