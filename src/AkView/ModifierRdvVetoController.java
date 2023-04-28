/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkView;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.RendezVous;
import entities.user;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.RendezVousService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author heha
 */
public class ModifierRdvVetoController implements Initializable {

    @FXML
    private ComboBox<String> decisiontf;
    @FXML
    private Button validerbtn;

    /**
     * Initializes the controller class.
     */
    public static final String ACCOUNT_SID = "AC002bd788f319e067fa0249d67368a519";
    public static final String AUTH_TOKEN = "2b906c8e9b71db0c27991a4a635f281c";
    UserService us = new UserService();
    
    int idrdv =0;
    
    int vetoId;
    String date,heure,raceanimal,nomanimal,decsion;
   
    
    public int VetoId (int vetoId){
    return this.vetoId = vetoId
;    }
    
            RendezVousService ps = new RendezVousService();
    public void setData(RendezVous c) {

        date=c.getDate();
        heure=c.getHeure();
        raceanimal=c.getRaeanimal();
        nomanimal=c.getNomanimal();
        vetoId=c.getUser_id();
        System.out.println("behi haw id l veterinaire " + vetoId);
        decisiontf.setValue(c.getDecision().toString());
        idrdv=c.getId();
    
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> listDecision = FXCollections.observableArrayList("Affecté","Non Affecté");
        decisiontf.setItems(listDecision);
    }    

    @FXML
    private void valider(ActionEvent event) {
        try{
        RendezVous s = new RendezVous();
        s.setUser_id(vetoId);
            System.out.println("hedha id l veto"+ vetoId);
        s.setId(idrdv);
            System.out.println("hedha id el rdv "+ idrdv);
        

            s.setDate(date);
            s.setHeure(heure);
            s.setRaeanimal(raceanimal);
            s.setNomanimal(nomanimal);
            s.setDecision(decisiontf.getValue());
            
            
        ps.modifier(s);
        System.out.println(s);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussi");
            alert.setHeaderText("Modification du Rendez-Vous réussi");
            alert.setContentText("Le Rendez-Vous a été Modifier avec succès !");
            alert.showAndWait();
        System.out.println("Rendez-Vous modifié avec succes");
        
         user veto = new user();
           veto = us.recupererUserByid(vetoId);
            System.out.println("hedha l veterinaire : " +veto);
            String from = "+16073897187"; // your Twilio phone number
    String to = "+21656106247"; // recipient phone number
        String bienvenue = "Salut Mr/Mmme MyVet Vous Informe Que Votre Rendez-Vous Pour L'Animal Nommée "+ nomanimal + " De La Race " + raceanimal +
                " Chez Le Vétérinaire "+ veto.getPrenom() + " "+ veto.getNom() + " Pour La Date "+ date + " et L'Heure "+ heure +
                " à Pris La Décision Suivante "+ decisiontf.getValue() + " Merci De Consulter Votre Compte ! ";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
               Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+21656106247"),
                    new com.twilio.type.PhoneNumber("+16073897187"), bienvenue).create();
            System.out.println(message.getSid());
            
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
            decisiontf.getScene().setRoot(borderPane);
                   
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
