/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkView;

import entities.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.AnimalService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AfficherListeAnimalController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    AnimalService ps = new AnimalService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        List<Animal> animal =new ArrayList<>();
        try {
            
           animal = ps.recuperer();
           
            int row = 1;
            int column = 0;
            for (int i = 0; i < animal.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Animal.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                AkView.AnimalController controller = loader.getController();
                controller.setAnimal(animal.get(i));
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
    
}
