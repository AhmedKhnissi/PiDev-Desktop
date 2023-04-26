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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
 
public class AfficherListeReclamationController implements Initializable {
ReclamationService rs = new ReclamationService();
        Reclamation r = new Reclamation();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        List<Reclamation> reclamation =new ArrayList<>();
        try {
            
           reclamation = rs.recuperer();
           
            int row = 1;
            int column = 0;
            for (int i = 0; i < reclamation.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamtion.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                GUI.ReclamationController controller = loader.getController();
                controller.setReclamation(reclamation.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            }
        } catch (SQLException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        }
    }    
    
    
     @FXML
    private void  AjouterReclamation(ActionEvent event) throws IOException {
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 1000);
        nouveauStage.setScene(scene);
    }
    
}
