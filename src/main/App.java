/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sa
 */
public class App extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        
        System.out.println("hello1");
        System.out.println("hello1");
        
        Parent root = FXMLLoader.load(getClass().getResource("/view_admin/UILogIn.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Log in");
        stage.show();
    }
    
}
