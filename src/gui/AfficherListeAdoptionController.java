/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Adopt;
import entities.User;
import entities.UserSession;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.AdoptService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class AfficherListeAdoptionController implements Initializable {
AdoptService rs = new AdoptService();
        Adopt r = new Adopt();
    @FXML
    private Button ajout;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    
    UserService us=new UserService();
    int idd = UserSession.getInstance().getId();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Adopt> adopt =new ArrayList<>();
        try {
            
           adopt = rs.recuperer();
           
            int row = 1;
            int column = 0;
            for (int i = 0; i < adopt.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Adopt.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                gui.AdoptController controller = loader.getController();
                controller.setAdopt(adopt.get(i));
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
        System.out.println("ahawa " + UserSession.getInstance().getRole());
    }    

    @FXML
    private void AjouterAdoption(ActionEvent event) throws IOException, SQLException {
        
        User u = us.veterinaireRole(idd);
      System.out.println("hedha howa role : "+u.getRole());
     try {if (u.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
        // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            ajout.getScene().setRoot(borderPane);
     }
     else if (u.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterAdoption.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            ajout.getScene().setRoot(borderPane);
     }

    } catch (IOException ex) {
        System.out.print("err");
    }}
        
        
  
    }
    

