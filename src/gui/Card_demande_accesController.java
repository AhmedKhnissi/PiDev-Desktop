/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Card_demande_accesController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label nom;
    @FXML
    private Button modifyItem1;
    @FXML
    private Button deletItemButton1;
    @FXML
    private Button supprimer;
    @FXML
    private Label email;
    @FXML
    private Label tel;
    @FXML
    private Label pays;
    @FXML
    private Label gouvernorat;
    @FXML
    private Label ville;
    @FXML
    private Label rue;
    @FXML
    private Button ACCEPTER;
    @FXML
    private Label id;
    @FXML
    private Label rue1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleModifyItem(ActionEvent event) {
    }

    @FXML
    private void handleDeleteItem(ActionEvent event) {
    }

    @FXML
    private void refuser(ActionEvent event) throws SQLException {
           UserService userservice=new UserService();

        int idValue = Integer.parseInt(id.getText());
        System.out.println("idddddddddddddddddd "+idValue);
        userservice.supprimer(idValue);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("refuser");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur a été refusé avec succès ");
        alert.showAndWait();
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("list_demande_acces.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void accepter(ActionEvent event) throws SQLException {
             UserService userservice=new UserService();

        int idValue = Integer.parseInt(id.getText());
        System.out.println("idddddddddddddddddd "+idValue);
        userservice.accepter(idValue);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("accepter");
        alert.setHeaderText(null);
        alert.setContentText("Utilisateur a été accepté avec succès ");
        alert.showAndWait();
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("list_demande_acces.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }
    
    public void setData(User vet) throws SQLException {
    id.setText(Integer.toString(vet.getId()));
    nom.setText(vet.getNom());
    email.setText(vet.getEmail());
    tel.setText(vet.getTel());
    pays.setText(vet.getPays());
    gouvernorat.setText(vet.getGouvernorat());
     ville.setText(vet.getVille());
    rue.setText(vet.getRue());    
    }

    @FXML
    private void GetPermisTravail(MouseEvent event) {
        
    }
    
}
