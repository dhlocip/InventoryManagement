/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller_inventory_manager;

import data.Category;
import data_modifier.CategoryModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class CreateCategoryController implements Initializable {

    private TextField searchTF;
    @FXML
    private Label errorCategoryName;
    @FXML
    private TextField descriptionTF;
    @FXML
    private Label errorDescription;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryIdCol;
    @FXML
    private TableColumn<Category, String> categoryNameCol;
    @FXML
    private TableColumn<Category, String> descriptionCol;
    @FXML
    private TextField categoryNameTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getListCategory();
        } catch (SQLException ex) {
            Logger.getLogger(CreateCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        hideErrorCategoryName(false);
        hideErrorDescription(false);

    }

    private void hideErrorCategoryName(boolean value) {
        errorCategoryName.setVisible(value);
        errorCategoryName.managedProperty().bind(errorCategoryName.visibleProperty());
    }

    private void hideErrorDescription(boolean value) {
        errorDescription.setVisible(value);
        errorDescription.managedProperty().bind(errorDescription.visibleProperty());
    }

    private void getListCategory() throws SQLException {
        ObservableList<Category> oList = new CategoryModifier().getListCategory();

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTableView.setItems(oList);
    }

    private void getListCategoryAfterSearch(String idOrName) throws SQLException {
        ObservableList<Category> oList = new CategoryModifier().getListCategoryAfterSearch(idOrName);

        categoryIdCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        categoryNameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTableView.setItems(oList);
    }

    private void searchReleased(KeyEvent event) throws SQLException {
        getListCategoryAfterSearch(searchTF.getText());
    }

    @FXML
    private void descriptionReleased(KeyEvent event) {
    }

    private boolean isCategoryRight() {
        String tmp = categoryNameTF.getText();
        return tmp.matches("^[a-zA-Z]+[\\s]?[a-zA-Z]+") && tmp.length() > 3;
    }

    private void checkCategoryName() {
        if (isCategoryRight()) {
            hideErrorCategoryName(false);
        } else {
            hideErrorCategoryName(true);
            errorCategoryName.setText("\'" + categoryNameTF.getText() + "\' is invalid.");
        }
    }

    @FXML
    private void categoryNameReleased(KeyEvent event) {
        checkCategoryName();
    }

    @FXML
    private void createCategoryClicked(MouseEvent event) throws SQLException {
        if (isCategoryRight()) {
            Category category = new Category();
            category.setCategoryName(categoryNameTF.getText());
            category.setDescription(descriptionTF.getText());
            if (new CategoryModifier().createCategory(category)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Create category is successfully.");
                alert.showAndWait();
                getListCategory();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Text fields are not empty.");
            alert.showAndWait();
            checkCategoryName();
        }
    }

}
