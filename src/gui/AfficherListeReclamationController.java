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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ReclamationService;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



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
    @FXML
    private TextField barreRecherche;
    
    
    @FXML
    private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //Create a KeyCodeCombination for the "N" shortcut
        KeyCodeCombination rShortcut = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        KeyCodeCombination cShortcut = new KeyCodeCombination(KeyCode.C);

        // Add a listener to the list view to detect when the "N" key is pressed
        anchorPane.setOnKeyPressed(keyEvent -> {
        if (rShortcut.match(keyEvent)) {
                    Stage nouveauStage;
        Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            grid.getScene().setRoot(borderPane);
            
            } catch (IOException ex) {
                Logger.getLogger(AfficherListeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        nouveauStage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();

Scene scene = new Scene(root, 1300, 1000);
        nouveauStage.setScene(scene);
            
        keyEvent.consume(); // Prevent the event from being processed further
    };
    if(cShortcut.match(keyEvent)){
        System.out.println("test");
    }
});



        List<Reclamation> reclamation =new ArrayList<>();
        try {
            
           reclamation = rs.recuperer();
           
            int row = 1;
            int column = 0;
            for (int i = 0; i < reclamation.size(); i++) {
                System.out.println("tmarech");
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamtion.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                gui.ReclamationController controller = loader.getController();
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
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            grid.getScene().setRoot(borderPane);
          
    }
    
    private void navaigateThroughShortcut() throws IOException{
               
    }
     @FXML
    private void BrreRecherche(KeyEvent event) throws SQLException{
        String recherche = barreRecherche.getText();
        List<Reclamation> rec = null;
        try {
            rec = rs.recuperer();
          //  System.out.println("hedhi liste de  rendez vous  li recuperineha : " + rdv );
        } catch (SQLException ex) {
            Logger.getLogger(AfficherListeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
// filtrer les rendez vous qui correspondent aux critères de recherche
        List<Reclamation> resultatsRecherche = rec.stream()
                .filter(s -> s.getName().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(resultatsRecherche.size());
        grid.getChildren().clear(); // Effacer tous les enfants du GridPane
        int row = 1;
        int column = 0;
        for (int i = 0; i < resultatsRecherche.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamtion.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AfficherListeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.ReclamationController controller = loader.getController();
            controller.setReclamation(resultatsRecherche.get(i));
            if (column > 0) {
                column = 0;
                row++;
            }
            grid.add(pane, column++, row);
            GridPane.setMargin(pane, new Insets(10));
        }
        barreRecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    BrreRecherche(event); //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherListeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    
}
}
