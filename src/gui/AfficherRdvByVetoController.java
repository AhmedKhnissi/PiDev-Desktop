/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.RendezVousService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AfficherRdvByVetoController implements Initializable {

    @FXML
    private TextField barreRecherche;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    
    int id;
    RendezVousService ps = new RendezVousService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void dynamicinitialize(int id) {
                try {
            this.id = id;
            List<RendezVous> rendez = ps.recupererRdvByVeto(id);
            int row = 0;
            int column = 0;
            for (int i = 0; i < rendez.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("RendezVous.fxml"));
                AnchorPane pane = loader.load();
                RendezVousController controller = loader.getController();
                controller.setRdv(rendez.get(i));
                

                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }

                grid.setAlignment(Pos.CENTER);
                grid.setHalignment(scrollPane, HPos.CENTER);
                grid.setValignment(scrollPane, VPos.CENTER);
                grid.getColumnConstraints().clear();
                grid.getRowConstraints().clear();
            }
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface vehicule : " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRapportByAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void BrreRechercherdv(KeyEvent event) {
        String recherche = barreRecherche.getText();
        List<RendezVous> rdv = null;
        try {
            rdv = ps.recuperer();
          //  System.out.println("hedhi liste de  rendez vous  li recuperineha : " + rdv );
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRdvByVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
// filtrer les rendez vous qui correspondent aux critères de recherche
        List<RendezVous> resultatsRecherche = rdv.stream()
                .filter(s -> s.getNomanimal().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(resultatsRecherche.size());
        grid.getChildren().clear(); // Effacer tous les enfants du GridPane
        int row = 1;
        int column = 0;
        for (int i = 0; i < resultatsRecherche.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RendezVous.fxml"));
                AnchorPane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherRdvByVetoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                RendezVousController controller = loader.getController();
                controller.setRdv(resultatsRecherche.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);
                
                GridPane.setMargin(pane, new Insets(10));
            } catch (SQLException ex) {
                Logger.getLogger(AfficherRdvByVetoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        barreRecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                BrreRechercherdv(event); //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
    
}
