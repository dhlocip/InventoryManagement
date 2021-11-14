/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void emailReleased(KeyEvent event) {
    }

    @FXML
    private void nextAction(ActionEvent event) {
    }
    
}
