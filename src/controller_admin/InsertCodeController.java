/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data_modifier.UserModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class InsertCodeController implements Initializable {

    String lEmail;

    @FXML
    private BorderPane homeBox;
    @FXML
    private TextField insertCodeTF;
    @FXML
    private Label errorInsertCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideErrorOfInsertCode(false);

    }

    public void setEmail(String email) {
        lEmail = email;
    }

    private void hideErrorOfInsertCode(boolean value) {
        errorInsertCode.setVisible(value);
        errorInsertCode.managedProperty().bind(errorInsertCode.visibleProperty());
    }

    private boolean isTokenRight() throws SQLException {
        String tmp = insertCodeTF.getText();
        return tmp.matches("[\\d]{6}") && new UserModifier().checkToken(insertCodeTF.getText(), lEmail);
    }

    private void checkToken() throws SQLException {
        if (isTokenRight()) {
            hideErrorOfInsertCode(false);
        } else {
            hideErrorOfInsertCode(true);
            errorInsertCode.setText(insertCodeTF.getText() + " is invalid.");
        }
    }

    @FXML
    private void insertCodeReleased(KeyEvent event) throws SQLException {
        hideErrorOfInsertCode(false);
        if (insertCodeTF.getText().length() >= 6) {
            checkToken();
        }
    }

    @FXML
    private void continueAction(ActionEvent event) throws IOException, SQLException {
        if (isTokenRight()) {
            // next page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view_admin/NewPassword.fxml"));
            Parent root = loader.load();

            NewPasswordController control = loader.getController();
            control.setEmail(lEmail);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("New password");
            stage.show();
        } else {
            checkToken();
        }
    }

}
