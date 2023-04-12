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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.RapportMedicalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AfficherRapportByAnimalController implements Initializable {
int id;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    RapportMedicalService ps = new RapportMedicalService();

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
            List<RapportMedical> vehicules = ps.recupererRapportByAnimal(id);
            int row = 0;
            int column = 0;
            for (int i = 0; i < vehicules.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("RapportMedical.fxml"));
                AnchorPane pane = loader.load();
                RapportMedicalController controller = loader.getController();
                controller.setRapport(vehicules.get(i));
                

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
}
