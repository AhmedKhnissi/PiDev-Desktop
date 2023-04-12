/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.RapportMedical;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RapportMedicalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class RapportMedicalController implements Initializable {

    @FXML
    private AnchorPane rapportLignes;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Button modifierbutton;
    @FXML
    private Button supprimerButton;

    /**
     * Initializes the controller class.
     */
    int animalId;
    private int idRapport;

    
       
    
       RapportMedical pe = new RapportMedical();
       RapportMedicalService aa = new RapportMedicalService();
    
public void setRapport(RapportMedical c) throws SQLException {
    idRapport = c.getId();
    descriptionlabel.setText(c.getDescription());
    
    pe.setAnimal_id(c.getAnimal_id());
    pe.setId(c.getId());
    pe.setDescription(c.getDescription());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
        BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRapport.fxml"));
            Parent root2 = loader1.load();
              GUI.ModifierRapportController controller = loader1.getController();

            controller.AnimalId(pe.getAnimal_id());
        System.out.println("hedha clé etranger rapport "+pe.getAnimal_id());}
        catch (IOException ex) {
            System.err.println("zab");
        }
    }    

    @FXML
    private void modifierRapoort(ActionEvent event) {
         try {
             
            System.out.println("NIK ZIBIIIIIIIII");
                
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRapport.fxml"));
            Parent root2 = loader1.load();
              GUI.ModifierRapportController controller = loader1.getController();

            controller.setData(pe);
            
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rapportLignes.getScene().setRoot(borderPane);
            
            
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

    @FXML
    private void supprimerRapport(ActionEvent event) throws IOException {
        
//        try {
//            ps.supprimer(pe);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//
//            siegesLignes.getScene().setRoot(root);
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer ce Rapport ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        try {
            aa.supprimer(pe);
                 
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeAnimal.fxml"));
            Parent root2 = loader1.load();
             // AfficherSiegeController controller = loader1.getController();

            //controller.setData(pe);
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rapportLignes.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    }
    
}
