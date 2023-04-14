/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author Khalil
 */
public class AjouterPublicationController implements Initializable {
    
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfTitre; 
    @FXML
    private TextArea taContenu;
    @FXML
    private TextField tfImage; 
    @FXML
    private Button ajouterbtn;  
    
    PublicationService ps = new PublicationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void ajouterPublication(ActionEvent event) { 
         LocalDate currentDate = LocalDate.now(); 
         Date sqlDate = Date.valueOf(currentDate);
           if (tfTitre.getText().isEmpty() || taContenu.getText().isEmpty() || tfAuteur.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !");
            al.show();
    } else{
               
              String auteur = tfAuteur.getText();
              String titre = tfTitre.getText();
              String contenu = taContenu.getText(); 
              String image = tfImage.getText(); 
            try {
                Publication p = new Publication(auteur, titre, contenu, image); 
                PublicationService ps = new PublicationService();
              
                p.setDatepub(sqlDate);
                p.setLikes(0); 
                p.setDislike(0); 
                p.setNbsignal(0);
                
                ps.ajouter(p);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("publication ajouté");
                al.setContentText("La publication est ajoutée avec Succès !!");
                al.show();
                
            } catch (NumberFormatException e) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Controle de saisie");
                al.setContentText("Le champ age doit etre numerique !!");
                al.show();
            } 
            
            
    
} 

    } 
    
    
    
}
