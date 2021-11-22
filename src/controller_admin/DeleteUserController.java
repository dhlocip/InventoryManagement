/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_admin;

import controller_app.UIDashboardAdminController;
import controller_app.UIDashboardInventoryManagerController;
import controller_app.UIDashboardSaleManagerController;
import data.User;
import data_modifier.BillDetailModifier;
import data_modifier.BillModifier;
import data_modifier.EventDetailModifier;
import data_modifier.EventModifier;
import data_modifier.ImportStockDetailModifier;
import data_modifier.ImportStockModifier;
import data_modifier.NewRequestModilfier;
import data_modifier.RequestDetailModifier;
import data_modifier.RequestModifier;
import data_modifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class DeleteUserController implements Initializable {

    String lUserId;
    String lPosition;
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

    @FXML
    private void userTableViewClicked(MouseEvent event) throws SQLException {
        User item = userTableView.getSelectionModel().getSelectedItem();

        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on a non-empty line.");
            alert.showAndWait();
        } else {
            lUserId = item.getPersonId();
            lPosition = item.getPosition();
            if (lPosition.equalsIgnoreCase("admin")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText("Error");
                alert.setContentText("Can not delete admin.");
                alert.showAndWait();
            } else if (lPosition.equalsIgnoreCase("sale manager")) {
                List<String> listEventId = new EventModifier().getListEventId(lUserId);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Confirm");
                alert.setContentText("Are you sure?\nClick OK to delete the line.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {

                    for (String listId : listEventId) {
                        new EventDetailModifier().deleteEventDetail(listId);
                    }

                    if (new EventModifier().deleteEvent(lUserId)) {
                        new UserModifier().deleteUser(lUserId);
                        getListUserInfo();
                    }
                }
            } else if (lPosition.equalsIgnoreCase("inventory manager")) {
                List<String> listImportStockId = new ImportStockModifier().getListImportStockId(item.getPersonId());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Confirm");
                alert.setContentText("Are you sure?\nClick OK to delete the line.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {

                    for (String listId : listImportStockId) {
                        new ImportStockDetailModifier().deleteImportStockDetail(listId);
                    }

                    if (new ImportStockModifier().deleteImportStock(lUserId)) {
                        new UserModifier().deleteUser(lUserId);
                        getListUserInfo();
                    }
                }
            } else {
                List<String> listBillId = new BillModifier().getListBillId(lUserId);
                List<String> listRequestId = new RequestModifier().getListRequestId(lUserId);
                List<String> listNewRequestId = new NewRequestModilfier().getListNewRequestId(lUserId);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Confirm");
                alert.setContentText("Are you sure?\nClick OK to delete the line.");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {

                    for (String listId : listBillId) {
                        new BillDetailModifier().deleteBillDetail(listId);
                    }

                    for (String listId : listRequestId) {
                        new RequestDetailModifier().deleteRequestDetail(listId);
                    }

                    for (String listId : listNewRequestId) {
                        new NewRequestModilfier().deleteNewRequestDetail(listId);
                    }

                    if (new BillModifier().deleteBill(lUserId)
                            && new RequestModifier().deleteRequest(lUserId)
                            && new NewRequestModilfier().deleteNewRequest(lUserId)) {
                        new UserModifier().deleteUser(lUserId);
                        getListUserInfo();
                    }
                }
            }
        }
    }

}
