/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.RendezVous;
import entities.User;
import entities.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
public class RendezVousController implements Initializable {

    @FXML
    private AnchorPane rdvLignes;
    @FXML
    private Label datelabel;
    @FXML
    private Label heurelabel;
    @FXML
    private Label racelabel;
    @FXML
    private Label nomlabel;
    @FXML
    private Button modifierbutton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button modifierbuttonveto;
    @FXML
    private Label decisionlabel;

    /**
     * Initializes the controller class.
     */
    
    int idVeto;
    private int idRdv;

RendezVous pe = new RendezVous();
       RendezVousService aa = new RendezVousService();
       int iddd = UserSession.getInstance().getId();
    UserService ps = new UserService();
    
       public void setRdv(RendezVous c) throws SQLException {
           User user = ps.veterinaireRole(iddd);
    idRdv = c.getId();
        System.out.println("hedha id session : "+UserSession.getInstance().getId()); 
        System.out.println("hedha id l veto  : "+idVeto); 
         if (user.getRole().equals("[\"ROLE_VETERINAIRE\"]")){modifierbutton.setVisible(false);}
    else if (user.getRole().equals("[\"ROLE_PROPRIETAIRE\"]") ){modifierbuttonveto.setVisible(false);}
     if (user.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){supprimerButton.setVisible(false);}
    System.out.println("id de rendez-vous"+ idRdv);
    datelabel.setText(c.getDate());
    System.out.println("id de rendez-vous"+ c.getDate());
    heurelabel.setText(c.getHeure());
    racelabel.setText(c.getRaeanimal());
    nomlabel.setText(c.getNomanimal());
    decisionlabel.setText(c.getDecision());
    
        pe.setUser_id(c.getUser_id());
        System.out.println("fel rendez vous controller haw l clé etrangére taa l veto  "+ pe.getUser_id());
        pe.setId(idRdv);
        
        System.out.println("fel rendez vous controller haw l clé primaire taa rendez vous "+ idRdv);
        pe.setDate(c.getDate());
        pe.setHeure(c.getHeure());
        pe.setNomanimal(c.getNomanimal());
        pe.setRaeanimal(c.getRaeanimal());
        pe.setDecision(c.getDecision());
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
         try {
        BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRdvProp.fxml"));
            Parent root2 = loader1.load();
              ModifierRdvPropController controller = loader1.getController();

            controller.VetoId(pe.getUser_id());
        System.out.println("haw cle etranger rendez-vous "+pe.getUser_id());}
        catch (IOException ex) {
            System.err.println("zah");
        }
    }    

    @FXML
    private void modifierRdv(ActionEvent event) {
         try {
             
            System.out.println("lodifier temchi !");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
        Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRdvProp.fxml"));
            Parent root2 = loader1.load();
             ModifierRdvPropController controller = loader1.getController();

            
            controller.setData(pe);
            
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rdvLignes.getScene().setRoot(borderPane);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerRdv(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer ce Rapport ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        try {
            User user = ps.veterinaireRole(iddd);
                                if (user.getRole().equals("[\"ROLE_VETERINAIRE\"]")){
            aa.supprimer(pe);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
                                     Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeVeto.fxml"));
            Parent root2 = loader1.load();
             
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rdvLignes.getScene().setRoot(borderPane);
          
        }
    
    
    else if (user.getRole().equals("[\"ROLE_PROPRIETAIRE\"]")){
            aa.supprimer(pe);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
                                     Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeVeto.fxml"));
            Parent root2 = loader1.load();
             
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rdvLignes.getScene().setRoot(borderPane);
          
        }} 
    
    catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    @FXML
    private void modifierVetoRdv(ActionEvent event) {
        try {
             
            System.out.println("modifier taa vétérinaire hedhi tmarech !");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_veterinaire.fxml"));
        Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRdvVeto.fxml"));
            Parent root2 = loader1.load();
              gui.ModifierRdvVetoController controller = loader1.getController();

            
            controller.setData(pe);
            
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            rdvLignes.getScene().setRoot(borderPane);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
