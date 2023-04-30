/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.RapportMedical;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.RapportMedicalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AjouterRapportController implements Initializable {

    @FXML
    private TextField descriptionid;
    @FXML
    private Button ajouterbtn;
int id;
    RapportMedicalService ps = new RapportMedicalService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void dynamicinitialize(int id) {
         this.id = id;
     }
     

    @FXML
    private void ajouter(ActionEvent event) {
          try {
              RapportMedical s = new RapportMedical();
              
              String description = descriptionid.getText();
            if(description == null || description.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'Description' est vide ! ");
            alert.setContentText("Veuillez Entrer Une Description Valide. ! ");
            alert.showAndWait();
            return;
        }
            s.setDescription(description);
            
            s.setAnimal_id(id);
            
            
              System.out.println(id);
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de Rapport réussi");
            alert.setContentText("Le Rapport a été ajouté avec succès !");
            alert.showAndWait();
            System.out.println("Rapport ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
