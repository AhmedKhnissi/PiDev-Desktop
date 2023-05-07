/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Adopt;
import entities.User;
import entities.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.AdoptService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class AdoptController implements Initializable {

    @FXML
    private AnchorPane animalPane;
    @FXML
    private Button Annuler;
    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label sterelisation;
    @FXML
    private Label age;
    @FXML
    private Label genre;
    @FXML
    private Button Modifier;
    @FXML
    private Label vaccination;
    @FXML
    private Label informations;

    /**
     * Initializes the controller class.
     */
    
    AdoptService rs = new AdoptService();
        Adopt pe = new Adopt();
         UserService us=new UserService();
    int idd = UserSession.getInstance().getId();
          
        public void setAdopt(Adopt c) {
    
        
    nom.setText(c.getNom());
    genre.setText(c.getGender());
     age.setText(String.valueOf(c.getAge()));
         System.out.println("age"+c.getAge());
         if (c.isSterelisation()) {
    sterelisation.setText("Oui");}
         else {sterelisation.setText("Non");}
                  if (c.isVaccination()) {
    vaccination.setText("Oui");}
         else {sterelisation.setText("Non");}
    
     
  
    informations.setText(c.getInformations());

    pe.setId(c.getId());
             System.out.println("hedha l id taa adoption :" + pe.getId());
    pe.setNom(c.getNom());
    pe.setGender(c.getGender());
    pe.setAge(c.getAge());
    pe.setSterelisation(c.isSterelisation());
    pe.setVaccination(c.isVaccination());
    pe.setImage(c.getImage());
    pe.setInformations(c.getInformations());
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void SupprimerAdoption(ActionEvent event) throws IOException, SQLException {
           
        User u = us.veterinaireRole(idd);
      System.out.println("hedha howa role : "+u.getRole());
     try {if (u.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
         rs.supprimer(pe);
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
     }
     else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
         rs.supprimer(pe);
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
     }

    } catch (IOException ex) {
        System.out.print("err");
    }
        
        
        
        try {
           
            System.out.println("s"+pe);
            rs.supprimer(pe);
                 
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAdoption.fxml"));
            Parent root2 = loader1.load();
             // AfficherSiegeController controller = loader1.getController();

            //controller.setData(pe);
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        
    }
    }

    @FXML
    private void ModifierAdoption(ActionEvent event) throws SQLException {
        
         User u = us.veterinaireRole(idd);
      System.out.println("hedha howa role : "+u.getRole());
     try {if (u.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierAdopt.fxml"));
            Parent root2 = loader1.load();
            gui.ModifierAdoptController controller = loader1.getController();
            controller.setData(pe);
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
     }
     else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierAdopt.fxml"));
            Parent root2 = loader1.load();
            gui.ModifierAdoptController controller = loader1.getController();
            controller.setData(pe);
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
     }

    } catch (IOException ex) {
        System.out.print("err");
    }
        
        
        
        } 
    }
    
    

