/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.RapportMedical;
import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RendezVousService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ModifierRdvPropController implements Initializable {

    @FXML
    private TextField dateid;
    @FXML
    private ComboBox<String> heureid;
    @FXML
    private TextField raceanimalid;
    @FXML
    private TextField nomanimalid;
    @FXML
    private Button modifierbtn;

    /**
     * Initializes the controller class.
     */
    
    int idrdv =0;
    
    int vetoId;
    public int VetoId (int vetoId){
    return this.vetoId = vetoId
;    }
    
            RendezVousService ps = new RendezVousService();
    public void setData(RendezVous c) {

        dateid.setText(c.getDate());
        heureid.setValue(c.getHeure());
        raceanimalid.setText(c.getRaeanimal());
        nomanimalid.setText(c.getNomanimal());
        vetoId=c.getUser_id();
        idrdv=c.getId();
    
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           ObservableList<String> listHeure = FXCollections.observableArrayList("09:00","10:00","11:00","12:00","14:00","15:00","16:00","17:00");
           heureid.setItems(listHeure);
    }    

    @FXML
    private void valider(ActionEvent event) {
     try{
        RendezVous s = new RendezVous();
        s.setUser_id(vetoId);
            System.out.println("hedha id l veto"+ vetoId);
        s.setId(idrdv);
            System.out.println("hedha id el rdv "+ idrdv);
        String date = dateid.getText();
        String heure = heureid.getValue();
        String raceanimal = raceanimalid.getText();
        String nomanimal = nomanimalid.getText();
        String decision = "Pas Prise Encore";

            s.setDate(date);
            s.setHeure(heure);
            s.setRaeanimal(raceanimal);
            s.setNomanimal(nomanimal);
            s.setDecision(decision);
            
            
            
     

        ps.modifier(s);
        System.out.println(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussi");
            alert.setHeaderText("Modification du Rendez-Vous réussi");
            alert.setContentText("Le Rendez-Vous a été Modifier avec succès !");
            alert.showAndWait();
        System.out.println("Rendez-Vous modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            
               BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeVeto.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            dateid.getScene().setRoot(borderPane);    
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
