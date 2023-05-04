/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Animal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class AfficherPersonnesController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          List<Animal> sieges =new ArrayList<>();
        int row = 1;
            int column = 0;
            for (int i = 0; i < sieges.size(); i++) {
                // Chargement dynamique d'une interface
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Animal.fxml"));
                AnchorPane pane = null;
            
              try {
                  pane = loader.load();
                  // Passage de paramètres
              } catch (IOException ex) {
                  Logger.getLogger(AfficherPersonnesController.class.getName()).log(Level.SEVERE, null, ex);
              }
                
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            }
    }    
    
}
