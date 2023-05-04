/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.User;
import entities.UserSession;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

int iddd = UserSession.getInstance().getId();
@Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO

    List<User> veto = new ArrayList<>();
    
    try {
        User user = ps.veterinaireRole(iddd);
        if (user.getRole().equals("[\"ROLE_VETERINAIRE\"]")) {

            try {
                veto = ps.recuperer_veterinaires();
                    

                int row = 1;
                int column = 0;
                for (int i = 0; i < veto.size(); i++) {
                   if (iddd == veto.get(i).getId()){
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

                }else if (!(iddd == veto.get(i).getId())){System.out.println("laaaasba");}}
            } catch (SQLException ex) {
                Logger.getLogger(AfficherListeVetoController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {
            try {
                veto = ps.recuperer_veterinaires();


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
        }




    } catch (IOException ex) {
        System.out.println("Erreur de chargement de l'interface utilisateur : " + ex.getMessage());
    }   catch (SQLException ex) {
            Logger.getLogger(AfficherListeVetoController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    @FXML
    private void OuvrirStat(ActionEvent event) throws IOException, SQLException{
        User user = ps.veterinaireRole(iddd);
        if (user.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("VillesByRdv.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        grid.getScene().setRoot(borderPane);}
        else if (user.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("VillesByRdv.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        grid.getScene().setRoot(borderPane);}
       
    }
    
}
