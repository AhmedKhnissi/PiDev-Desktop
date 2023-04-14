/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package myvet_pidev;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField mot_de_passe;
    @FXML
    private Button login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mdp_oublie(MouseEvent event) {
    }

    @FXML
    private void register_veterinaire(MouseEvent event) {
                try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Veterinaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void register_proprietaire(MouseEvent event) {
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Proprietaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void register_magasin(MouseEvent event) {
                      try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Ajouter_Magasin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void authenticate(ActionEvent event) throws IOException {
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
