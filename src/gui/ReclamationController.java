/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Reclamation;
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
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class ReclamationController implements Initializable {

    @FXML
    private AnchorPane animalPane;
    @FXML
    private Button Annuler;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label message;
    @FXML
    private Label etat;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    
    
    ReclamationService rs = new ReclamationService();
        Reclamation pe = new Reclamation();
         public void setReclamation(Reclamation c) {
    
        
    name.setText(c.getName());
    email.setText(c.getEmail());
    subject.setText(c.getSubject());
    message.setText(c.getMessage());
    etat.setText(c.getEtat());
    
    pe.setId(c.getId());
             System.out.println("hedha l id taa reclamtion :" + pe.getId());
    pe.setName(c.getName());
    pe.setEmail(c.getEmail());
    pe.setSubject(c.getSubject());
    pe.setMessage(c.getMessage());
    pe.setEtat(c.getEtat());
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AnnulerReclamation(ActionEvent event) throws IOException {
              
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
   
 

        System.out.println("etat " + pe.getEtat());
    if ("non_traitee".equals(pe.getEtat()) || "en_cours".equals(pe.getEtat())){
        try {
           
    
            rs.supprimer(pe);
                 
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeReclamtion.fxml"));
            Parent root2 = loader1.load();
             // AfficherSiegeController controller = loader1.getController();

            //controller.setData(pe);
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            email.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        
    }}
    else {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Annulation Refusée !");
    alert.setHeaderText(null);
    alert.setContentText("Cette Reclamation ne peut pas être annulée !");
     Optional<ButtonType> result = alert.showAndWait();}
    }

    @FXML
    private void ModifierReclamation(ActionEvent event) {
        if ("non_traitee".equals(pe.getEtat()) || "en_cours".equals(pe.getEtat())){
        
             try {
             
            System.out.println("modif jawek behi");
                
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));
            Parent root2 = loader1.load();
              GUI.ModifierReclamationController controller = loader1.getController();

            controller.setData(pe);
            
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            email.getScene().setRoot(borderPane);
            
            
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
             else{
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Modification Refusée !");
    alert.setHeaderText(null);
    alert.setContentText("Cette Reclamation ne peut pas être modifié !");
     Optional<ButtonType> result = alert.showAndWait();}
    }
    
}
