/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.UserSession;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class NavbarAdminController implements Initializable {

    @FXML
    private Button btnprofile;
    @FXML
    private Button btnvet;
    @FXML
    private Button btnda;
    @FXML
    private Button btnprop;
    @FXML
    private Button btnmagasin;
    @FXML
    private Button btndec;
    @FXML
    private Button reclamation;
    @FXML
    private Button pub;
    @FXML
    private Button btnstat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void detail_profile(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.print(ex);
        }
    
    }

    @FXML
    private void list_veterinaire(ActionEvent event) {
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
    private void Demande_acces(ActionEvent event) {
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
    private void list_proprietaire(ActionEvent event) {
                try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("List_proprietaire.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void list_magasin(ActionEvent event) {
                try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("List_magasin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void deconnexion(ActionEvent event) {
 UserSession session=UserSession.getInstance();
        session.setId(null);
        session.setNom(null);
        session.setPrenom(null);
        session.setPassword(null);
        session.setEmail(null);
        session.setPays(null);
        session.setGouvernorat(null);
        session.setVille(null);
        session.setRue(null);
        session.setTel(null);
        session.setIsLoggedIn(false);

                         try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void list_reclamation(ActionEvent event) {
    }

    @FXML
    private void list_pub(ActionEvent event) {
        try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("AffichageAdmin.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }

    @FXML
    private void statistique(ActionEvent event) {
                            try{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("Userstatistique.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        nouveauStage.setScene(scene);
        }catch(IOException ex){
          System.out.println("nooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnn");
        }
    }
    
}
