/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data_modifier.SendEmail;
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
public class ForgotPasswordController implements Initializable {

    @FXML
    private BorderPane homeBox;
    @FXML
    private TextField emailTF;
    @FXML
    private Label errorEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideErrorOfEmail(false);
    }

    private void hideErrorOfEmail(boolean value) {
        errorEmail.setVisible(value);
        errorEmail.managedProperty().bind(errorEmail.visibleProperty());
    }

    private boolean isEmailRight() throws SQLException {
        String tmp = emailTF.getText();
        return tmp.matches("^[^\\W\\d]{1}[\\w]+[.]?[\\w]+[@]{1}[^\\W\\d]{4,7}[.]{1}[^\\W\\d]{3}[.]{0,1}[^\\W\\d]{0,3}[.]{0,1}[^\\W\\d]{0,2}")
                && new UserModifier().emailIsExists(tmp);
    }

    private void checkEmail() throws SQLException {
        if (isEmailRight()) {
            hideErrorOfEmail(false);
        } else {
            hideErrorOfEmail(true);
            errorEmail.setText("\"" + emailTF.getText() + "\" is invalid.");
        }
    }

    @FXML
    private void emailReleased(KeyEvent event) throws SQLException {
        hideErrorOfEmail(false);
        checkEmail();
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
    private void nextAction(ActionEvent event) throws IOException, SQLException {
        if (isEmailRight()) {
            String token = getRandomNumberString();
            String info = "\nYour account can’t be accessed without this verification code.";
            if (new SendEmail().send("Verification code", "Your verification code is: \n" + token + info, emailTF.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Send verification code is successfully. \nClick OK to the next page.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // update token
                    new UserModifier().updateToken(token, emailTF.getText());

                    // next page
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view_admin/InsertCode.fxml"));
                    Parent root = loader.load();

                    InsertCodeController control = loader.getController();
                    control.setEmail(emailTF.getText());

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Insert code");
                    stage.show();
                }
            }
        } else {
            checkEmail();
        }
    }

}
