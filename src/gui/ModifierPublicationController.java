/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Publication;
import java.net.URL;
import java.sql.SQLException;
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
public class ModifierPublicationController implements Initializable {

    
    @FXML
    private TextField tfAuteur;
    @FXML
    private TextField tfTitre; 
    @FXML
    private TextArea taContenu;
    @FXML
    private TextField tfImage; 
    @FXML
    private Button modifierbtn;    
    
    private static Publication selectedPublication;
    
    PublicationService ps = new PublicationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
     
        // TODO
    }     
    

public static void setPublication(Publication publication) {
    selectedPublication = publication;
}  


@FXML
private void updatePublication(ActionEvent event) { 
    if (tfTitre.getText().isEmpty() || taContenu.getText().isEmpty() || tfAuteur.getText().isEmpty() || tfImage.getText().isEmpty()) {
        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Controle de saisie");
        al.setContentText("Veuillez remplir tous les champs !");
        al.show();
    } else {
        String auteur = tfAuteur.getText();
        String titre = tfTitre.getText();
        String contenu = taContenu.getText(); 
        String image = tfImage.getText(); 
        
        // Retrieve the selected publication from the field in the controller
        Publication selectedPub = ModifierPublicationController.selectedPublication; 
        
        //  
        
        
        // Update the publication object with the new values
        selectedPub.setAuteur(auteur);
        selectedPub.setTitre(titre);
        selectedPub.setContenu(contenu);
        selectedPub.setImage(image);
        
        PublicationService ps = new PublicationService();
        try {
            ps.modifier(selectedPub);
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Publication modifiée");
            al.setContentText("La publication a été modifiée avec succès !");
            al.show();
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Erreur");
            al.setContentText("Impossible de modifier la publication : " + ex.getMessage());
            al.show();
        } 
    } 
}

    
    
    
    
    
}
