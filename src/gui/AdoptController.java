/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Adopt;
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
    private void SupprimerAdoption(ActionEvent event) throws IOException {
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
    private void ModifierAdoption(ActionEvent event) {
        try {
             
            System.out.println("modifier");
                
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierAdopt.fxml"));
            Parent root2 = loader1.load();
            GUI.ModifierAdoptController controller = loader1.getController();
            controller.setData(pe);
            
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierSiege.fxml"));
//            Parent root = loader.load();
//            ModifierSiegeController controller = loader.getController();
//
//            controller.setData(pe);
//
//            siegesLignes.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
