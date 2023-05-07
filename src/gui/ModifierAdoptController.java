/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Adopt;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.AdoptService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class ModifierAdoptController implements Initializable {

    @FXML
    private TextField genreid;
    @FXML
    private TextField ageid;
    @FXML
    private TextField nomid;
    @FXML
    private Button modifierbtn;
    @FXML
    private TextField informaationsid;
    @FXML
    private ImageView image;
    @FXML
    private CheckBox sterilisation;
    @FXML
    private CheckBox vaccination;

    /**
     * Initializes the controller class.
     */
     int adoid;
    AdoptService ps = new AdoptService();
    Adopt adopt = new Adopt();
    
     public void setData(Adopt a) {

        nomid.setText(a.getNom());
        genreid.setText(a.getGender());
        ageid.setText(String.valueOf(a.getAge()));
         System.out.println("age"+a.getAge());
        sterilisation.setText(String.valueOf(a.isSterelisation()));
        vaccination.setText(String.valueOf(a.isVaccination()));
        
        informaationsid.setText(a.getInformations());
        
        adoid = a.getId();
        
    System.out.println("id" + adoid);
    
    
    adopt.setId(adoid);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) {
        
        try{
        Adopt s = new Adopt();
       
        String nom = nomid.getText();
        String genre = genreid.getText();
        Integer age = ageid.getLength();
         //String image = image.getText();
          String informations = informaationsid.getText();
          
            s.setId(adoid);
            s.setNom(nom);
            s.setGender(genre);
            s.setAge(age);
            s.setSterelisation(s.isSterelisation());
            s.setVaccination(s.isVaccination());
            
            s.setInformations(informations);
            
            
     

        ps.modifier(s);
        System.out.println(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussi");
            alert.setHeaderText("Modification de l'adoption réussi");
            alert.setContentText("Votre Adoption a été Modifier avec succès !");
            alert.showAndWait();
        System.out.println("Adoption modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
     
    }
    

    @FXML
    private void uploadimage(MouseEvent event) {
    }
    
}
