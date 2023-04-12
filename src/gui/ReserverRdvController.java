/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.RendezVous;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.RendezVousService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ReserverRdvController implements Initializable {

    @FXML
    private TextField dateid;
    @FXML
    private TextField heureid;
    @FXML
    private TextField raceanimalid;
    @FXML
    private TextField nomanimalid;
    @FXML
    private Button ajouterbtn;
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
         this.id = id;
     }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
              RendezVous s = new RendezVous();
              
            s.setDate(dateid.getText());
            s.setHeure(heureid.getText());
            s.setRaeanimal(raceanimalid.getText());
            s.setNomanimal(nomanimalid.getText());
            s.setDecision("Pas Prise Encore");
            s.setUser_id(id);
              System.out.println(id);
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de Rendez-Vous réussi");
            alert.setContentText("Le Rendez-Vous a été ajouté avec succès !");
            alert.showAndWait();
            System.out.println("Rendez-Vous ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
