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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.RendezVousService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ReserverRdvController implements Initializable {

    @FXML
    private ComboBox<String> decisiontf;
    @FXML
    private TextField dateid;
    @FXML
    private TextField heureid;
    
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
        ObservableList<String> listCategorie = FXCollections.observableArrayList("Chien","Chat","Lapin","Oiseau","Singe","Agneau","Vache");
        decisiontf.setItems(listCategorie);
    }    
     public void dynamicinitialize(int id) {
         this.id = id;
     }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
              RendezVous s = new RendezVous();
              
              
              String date = dateid.getText();
                if(date == null || date.trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Le champ 'date' est vide !");
                    alert.setContentText("Veuillez entrer une date valide sous la forme JJ/MM/YYYY.");
                    alert.showAndWait();
                    return;
                }
                if(!date.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Format de date invalide !");
                    alert.setContentText("La date doit être sous la forme JJ/MM/YYYY avec JJ entre 1 et 31, MM entre 1 et 12 et YYYY étant une année à 4 chiffres.");
                    alert.showAndWait();
                    return;
                }

            s.setDate(date);
            
            
            String heure = heureid.getText();
            if (heure == null || heure.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'heure' est vide !");
                alert.setContentText("Veuillez entrer une heure valide sous la forme HH:MM.");
                alert.showAndWait();
                return;
            }

            Pattern heurePattern = Pattern.compile("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
            Matcher heureMatcher = heurePattern.matcher(heure);

            if (!heureMatcher.matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Format d'heure incorrect !");
                alert.setContentText("L'heure doit être au format HH:MM avec HH entre 00 et 23 et MM entre 00 et 59.");
                alert.showAndWait();
                return;
            }

            s.setHeure(heure);
            
            String raceanimal = decisiontf.getValue();
            if (raceanimal == null || raceanimal.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'Race Animal' est vide !");
                alert.setContentText("Veuillez Choisir la Catégorie de Votre Animal");
                alert.showAndWait();
                return;
            }
            s.setRaeanimal(raceanimal);
            
            String nomanimal = nomanimalid.getText();
            if (nomanimal == null || nomanimal.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le champ 'Nom Animal' est vide !");
                alert.setContentText("Veuillez Ecrivez le Nom de Votre Animal");
                alert.showAndWait();
                return;
            }
            s.setNomanimal(nomanimal);
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
