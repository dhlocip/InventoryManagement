/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import data.User;
import data_modifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewUserController implements Initializable {

    String lSearch;
    
    @FXML
    private TextField searchTF;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> userIdCol;
    @FXML
    private TableColumn<User, String> fullNameCol;
    @FXML
    private TableColumn<User, String> birthdayCol;
    @FXML
    private TableColumn<User, String> hireDateCol;
    @FXML
    private TableColumn<User, String> addressCol;
    @FXML
    private TableColumn<User, String> phoneCol;
    @FXML
    private TableColumn<User, String> genderCol;
    @FXML
    private TableColumn<User, String> shiffCol;
    @FXML
    private TableColumn<User, String> userNameCol;
    @FXML
    private TableColumn<User, String> passwordCol;
    @FXML
    private TableColumn<User, String> positionCol;
    @FXML
    private TableColumn<User, String> emailCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getListUserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ViewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void getListUserInfo() throws SQLException {
        ObservableList<User> oList = new UserModifier().getListUser();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("personId"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        shiffCol.setCellValueFactory(new PropertyValueFactory<>("shiff"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTableView.setItems(oList);
    }
    
    private void getListUserAfterSearch(String idOrName) throws SQLException {
        ObservableList<User> oList = new UserModifier().findUser(idOrName);
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("personId"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        shiffCol.setCellValueFactory(new PropertyValueFactory<>("shiff"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTableView.setItems(oList);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        lSearch = searchTF.getText();
        getListUserAfterSearch(lSearch);
    }

}
