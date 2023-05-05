/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entities.Reclamation;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Soulaima_matmati
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Button retourbtn;
    @FXML
    private TextField subjectid;
    @FXML
    private TextField nomid;
    @FXML
    private Button ajouterbtn;
    @FXML
    private TextField emaimid;
    @FXML
    private TextField messageid;

    /**
     * Initializes the controller class.
     */
    ReclamationService ps = new ReclamationService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sidebar_proprietaire.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
               
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherListeReclamtion.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1,new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nomid.getScene().setRoot(borderPane);
          
        
        
         
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, AWTException {
         try {
              Reclamation s = new Reclamation();
              
              String nom = nomid.getText();
            if(nom == null || nom.trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'Nom' est vide ! ");
            alert.setContentText("Veuillez Entrer Un Nom Valide. ! ");
            alert.showAndWait();
            return;
        }
            String message = messageid.getText();
            if(message == null || message.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'Message' est vide ! ");
            alert.setContentText("Veuillez Entrer Un Message Valide. ! ");
            alert.showAndWait();
            return;
        }
            
            
              String email = emaimid.getText();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
        + "[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
             Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email.trim().isEmpty() || !pat.matcher(email).matches()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("La case 'Email' est vide ou contient un format invalide !");
        alert.setContentText("Veuillez entrer une adresse e-mail valide.");
         alert.showAndWait();
        return;
        }
              
              
              String subject = subjectid.getText();
              if(subject == null || subject.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'Sujet' est vide ! ");
            alert.setContentText("Veuillez Entrer Un Sujet Valide. ! ");
            alert.showAndWait();
            return;
        }
              
              
              
              
            s.setName(nom);
            
            s.setEmail(email);
            s.setSubject(subject);
            s.setMessage(message);
            s.setEtat("non_traitee");
            
            
              
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de Reclamation réussi");
            alert.setContentText("Votre Reclamation a été ajouté avec succès !");
            alert.showAndWait();
            if (SystemTray.isSupported()) {
             String imagePath = "/images/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString()); 
                BufferedImage awtImage = SwingFXUtils.fromFXImage(img1, null);
                TrayIcon trayIcon = new TrayIcon(awtImage, "Notification Title");
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Votre Reclamation a bien été ajouté", "L'admin va Bientot Traitée votre Reclamation", TrayIcon.MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Could not add TrayIcon to SystemTray");}
        
       } 
            
            
            else {
        System.err.println("SystemTray is not supported");
            System.out.println("Reclamation ajouter avec succes");
        } 
            
         }
            catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }}
    

