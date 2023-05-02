/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Animal;
import entities.User;
import entities.UserSession;
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
import services.UserService;

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
    UserService us = new UserService();
    
    AnimalService ps = new AnimalService();
    int iddd = UserSession.getInstance().getId();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
        List<Animal> animal =new ArrayList<>();
        try {
            
           animal = ps.recuperer();
           User u = us.veterinaireRole(iddd);
            int row = 1;
            int column = 0;
            for (int i = 0; i < animal.size(); i++) {
                if (u.getRole().equals("[\"ROLE_VETERINAIRE\"]")) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Animal.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                AnimalController controller = loader.getController();
                controller.setAnimal(animal.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));}
                else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]") && UserSession.getInstance().getId() == animal.get(i).getAnimals_id())
                {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Animal.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                AnimalController controller = loader.getController();
                controller.setAnimal(animal.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));
                }

            }
        } catch (SQLException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur  : " + ex.getMessage());
        }
    }        
    
}
