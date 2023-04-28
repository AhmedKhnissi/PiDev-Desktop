/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkView;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AfficherListeVetoController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    UserService ps = new UserService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        List<user> veto =new ArrayList<>();
        try {
            
            try {
                veto = ps.recuperer();
            
           
            int row = 1;
            int column = 0;
            for (int i = 0; i < veto.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("veterinaire.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                VeterinaireController controller = loader.getController();
                controller.setVeto(veto.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            }
            } catch (SQLException ex) {
                Logger.getLogger(AfficherListeVetoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }
    
    @FXML
    private void OuvrirStat(ActionEvent event) throws IOException{
        Stage nouveauStage;
        Parent root = FXMLLoader.load(getClass().getResource("VillesByRdv.fxml"));
        nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1300, 1000);
        nouveauStage.setScene(scene);
    }
    
}
