/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField subjectid;
    @FXML
    private TextField messageid;
    @FXML
    private Button modifierbtn;
    @FXML
    private TextField emailid;

    /**
     * Initializes the controller class.
     */
    int recid;
    ReclamationService ps = new ReclamationService();
    Reclamation reclamation = new Reclamation();
    public void setData(Reclamation c) {

        nameid.setText(c.getName());
        emailid.setText(c.getEmail());
        subjectid.setText(c.getSubject());
        messageid.setText(c.getMessage());
        
        recid = c.getId();
        
    System.out.println("id" + recid);
    
    
    reclamation.setId(recid);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void valider(ActionEvent event) {
        
        try{
        Reclamation s = new Reclamation();
       
        String name = nameid.getText();
        String email = emailid.getText();
        String subject = subjectid.getText();
        String message = messageid.getText();
          s.setEtat("non_traitee");
            s.setId(recid);
            s.setName(name);
            s.setEmail(email);
            s.setSubject(subject);
            s.setMessage(message);
            
            
            
     

        ps.modifier(s);
        System.out.println(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussi");
            alert.setHeaderText("Modification du Reclamation réussi");
            alert.setContentText("Votre Reclamation a été Modifier avec succès !");
            alert.showAndWait();
        System.out.println("Reclamation modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeReclamtion.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nameid.getScene().setRoot(borderPane);
          
            
            
             
            
//            
//            
//            
//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//            AfficherSiegeController controller = loader.getController();        
//            nomsiegetf.getScene().setRoot(root);           
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
    
    }
    
    

