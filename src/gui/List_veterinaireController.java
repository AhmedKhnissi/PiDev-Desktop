/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */

public class List_veterinaireController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private UserService userservice;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private GridPane UsertGrid;
    
    private ObservableList<User> alluser = FXCollections.observableArrayList();
     private List<User> listveterinaires;
    @FXML
    private Button retour;
     
     

     
     
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          System.out.println("helllllllllllooooooooooooooooooooooooo initialize");
        try {
            listProduitfeed();
        } catch (SQLException | IOException ex) {
          System.out.println("erreur liste veterinaire");

        }
         System.out.println("helllllllllllooooooooooooooooooooooooo fin initialize");

        
    } 
    
    
        private void listProduitfeed() throws SQLException, IOException {
        userservice= new UserService();
        int row =1 ;
        int col=0;
                 System.out.println("helllllllllllooooooooooooooooooooooooo initialize  222");

        
            listveterinaires = userservice.recuperer_veterinaires();
                     System.out.println("helllllllllllooooooooooooooooooooooooo debut boucle");

            for (User vet : listveterinaires) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Card_Veterinaire.fxml"));
                VBox vbox = fxmlLoader.load();
                vbox.setStyle("-fx-background-color: #F7F8FD;");
                Card_VeterinaireController cardController = fxmlLoader.getController();
                cardController.setData(vet);
                if (col == 3) {
                    col = 0;
                    row++;
                }
                UsertGrid.add(vbox, col++, row);
                UsertGrid.setMargin(vbox, new Insets(10));
                         System.out.println("helllllllllllooooooooooooooooooooooooo fin pour");

            }

    }

    @FXML
    private void retour_acceuil(ActionEvent event) {
                        try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("NavbarAdmin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }
    
    
    
 
}