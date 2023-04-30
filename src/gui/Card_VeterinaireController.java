/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.File;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import entities.User;
import entities.UserSession;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Card_VeterinaireController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Label nom;

    @FXML
    private Button supprimer;
    @FXML
    private Label prenom;
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
    private Button bloquer;
    @FXML
    private Label id;
    @FXML
    private Button debloquer;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
  
        // TODO
    }    



    @FXML
private void supprimer(ActionEvent event) throws SQLException {
    
       UserService userservice=new UserService();

        int idValue = Integer.parseInt(id.getText());
        System.out.println("idddddddddddddddddd "+idValue);
        userservice.supprimer(idValue);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("supprimer");
        alert.setHeaderText(null);
        alert.setContentText("vétérinaire a été supprimé avec succès ");
        alert.showAndWait();
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("List_veterinaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    } 


    @FXML
    private void bloquer(ActionEvent event) throws SQLException {
                UserService userservice=new UserService();

        int idValue = Integer.parseInt(id.getText());
        System.out.println("idddddddddddddddddd "+idValue);
        userservice.bloquer(idValue);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bloquer");
        alert.setHeaderText(null);
        alert.setContentText("veterinaire a été bloqué avec succès ");
        alert.showAndWait();
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("List_veterinaire.fxml"));
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
    prenom.setText(vet.getPrenom());
    email.setText(vet.getEmail());
    tel.setText(vet.getTel());
    pays.setText(vet.getPays());
    gouvernorat.setText(vet.getGouvernorat());
    ville.setText(vet.getVille());
    rue.setText(vet.getRue());
    //if(vet.getBloque()==1){
    //bloquer.setVisible(false);}
    //else{
      //debloquer.setVisible(false);
     //}
    }

    @FXML
    private void deploquer(ActionEvent event) throws SQLException {
                UserService userservice=new UserService();

        int idValue = Integer.parseInt(id.getText());
        System.out.println("idddddddddddddddddd "+idValue);
        userservice.debloquer(idValue);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("bloquer");
        alert.setHeaderText(null);
        alert.setContentText("veterinaire a été débloqué avec succès ");
        alert.showAndWait();
                   try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("List_veterinaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }
    
    
}
